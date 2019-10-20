From node:lts as builder
#Create and set the working directory, add ENV to PATH
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
ENV PATH /usr/src/app/node_modules/.bin:$PATH
#COPY files and build the app
COPY package.json /usr/src/app/package.json
COPY startContainer.sh /usr/src/app/startContainer.sh
RUN npm install
COPY . /usr/src/app
RUN npm run build

#Build nginx to serve app
FROM nginx:stable
#remove default nginx config and replace with modified one
RUN rm -rf /etc/nginx/conf.d
COPY conf /etc/nginx

#COPY the app from the first step of this dockerfile
COPY --from=builder /usr/src/app/build /usr/share/nginx/html
COPY --from=builder /usr/src/app/startContainer.sh /usr/share/startContainer.sh
EXPOSE 80
RUN chmod 777 /usr/share/startContainer.sh
CMD /usr/share/startContainer.sh
