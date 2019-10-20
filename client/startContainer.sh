#!/bin/sh
#echo "Swapping env variables."
#sed -i -e s/__ENV__/$REACT_APP_STAGE/g usr/share/nginx/html/index.html
#sed -i -e "s|\"__CONFIG__\"|$(cat /config/client-config-dev.json)|" /usr/share/nginx/html/index.html
echo "starting nginx"
nginx -g 'daemon off;'