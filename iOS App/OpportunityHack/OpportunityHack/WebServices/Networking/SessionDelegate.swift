//
//  SessionDelegate.swift

import Foundation

class SessionDelegate: NSObject, URLSessionDelegate, URLSessionTaskDelegate, URLSessionDataDelegate {
    fileprivate var subdelegates: [Int: TaskDelegate]
    fileprivate let subdelegateQueue = DispatchQueue(label: "subdelegateQueue", attributes: DispatchQueue.Attributes.concurrent)
    var sessionDidFinishEventsForBackgroundURLSession: ((Foundation.URLSession) -> Void)?

    subscript(task: URLSessionTask) -> TaskDelegate? {
        get {
            var subdelegate: TaskDelegate?
            subdelegateQueue.sync {
                subdelegate = self.subdelegates[task.taskIdentifier]
            }
            
            return subdelegate
        }
        
        set {
            /*
            dispatch_barrier_async: create a synchronization point within a concurrent dispatch queue. When it encounters a barrier, a concurrent queue delays the execution of the barrier block (or any further blocks) until all blocks submitted before the barrier finish executing
            */
            subdelegateQueue.async(flags: .barrier, execute: {
                self.subdelegates[task.taskIdentifier] = newValue
            }) 
        }
    }
    
    required override init() {
        self.subdelegates = Dictionary()
        super.init()
    }
    
    // MARK: NSURLSessionTaskDelegate
    func urlSession(_ session: URLSession, task: URLSessionTask, didCompleteWithError error: Error?) {
        if let delegate = self[task] {
            delegate.URLSession(session, task: task, didCompleteWithError: error)
            self[task] = nil
        }
    }
    
    func urlSession(_ session: URLSession, dataTask: URLSessionDataTask, didReceive response: URLResponse, completionHandler: (@escaping (Foundation.URLSession.ResponseDisposition) -> Void)) {
        if let delegate = self[dataTask] {
            delegate.URLSession(session, dataTask: dataTask, didReceiveResponse: response, completionHandler: completionHandler)
        }
    }

    func urlSession(_ session: URLSession, dataTask: URLSessionDataTask, didReceive data: Data) {
        if let delegate = self[dataTask] {
            delegate.URLSession(session, dataTask: dataTask, didReceiveData: data)
        }
    }
    
    func urlSessionDidFinishEvents(forBackgroundURLSession session: URLSession) {
        sessionDidFinishEventsForBackgroundURLSession?(session)
    }
}
