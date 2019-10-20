//
//  TaskDelegate.swift

import Foundation

class TaskDelegate: NSObject {
    
    let task: URLSessionDataTask
    let queue: DispatchQueue
    let progress: Progress
    
    var downloadedData: NSMutableData
    fileprivate(set) var error: Error?
    
    fileprivate var expectedContentLength: Int64?
    var dataProgress: ((_ bytesReceived: Int64, _ totalBytesReceived: Int64, _ totalBytesExpectedToReceive: Int64) -> Void)?
    var taskNeedNewBodyStream: ((Foundation.URLSession, URLSessionTask) -> InputStream?)?

    init(task: URLSessionDataTask) {
        
        self.downloadedData = NSMutableData()
        self.task = task
        self.progress = Progress(totalUnitCount: 0)
        self.queue = {
            let label:String = "com.ishan.task-\(task.taskIdentifier)"
            let queue:DispatchQueue = DispatchQueue(label: label, attributes: [])
            
            queue.suspend()
            
            return queue
            }()
    }
    
    func URLSession(_ session: Foundation.URLSession?, task: URLSessionTask?, didCompleteWithError error: Error?) {
        if let err = error {
            self.error = err
        }
        queue.resume()
    }
    
    func URLSession(_ session: Foundation.URLSession?, dataTask: URLSessionDataTask?, didReceiveResponse response: URLResponse?, completionHandler: ((Foundation.URLSession.ResponseDisposition) -> Void)!) {
        
        let disposition: Foundation.URLSession.ResponseDisposition = .allow
        if let res = response {
            expectedContentLength = res.expectedContentLength
        }
        completionHandler(disposition)
    }
    
    func URLSession(_ session: Foundation.URLSession?, dataTask: URLSessionDataTask?, didReceiveData data: Data?) {
        guard let d = data else {
            return
        }
        
        downloadedData.append(d)
        if let t = dataTask , let res = t.response , let dProgress = dataProgress {
            dProgress(Int64(d.count), Int64(downloadedData.length), res.expectedContentLength)
        }
    }
}
