# echo Publisher2
# g++ -Wall src/Publisher2.cpp -o bin/Publisher2 -lczmq -lzmq -O0 -g
# echo Subscriber2
# g++ -Wall src/Subscriber2.cpp -o bin/Subscriber2 -lczmq -lzmq -O0 -g
echo Prox2
gcc -Wall src/Proxy2.c -o bin/Proxy2 -lczmq -lzmq -lpthread -O0 -g
echo Publisher
g++ -Wall -Ilib/ src/Publisher.cpp -o bin/Pub -lczmq -lzmq -O0 -g
echo Subscriber
g++ -Wall -Ilib/ src/Subscriber.cpp -o bin/Sub -lczmq -lzmq -O0 -g