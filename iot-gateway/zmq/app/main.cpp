//#include "uart.h"
#include "Subscriber.h"
#include "Publisher.h"

using namespace std;


int main(int nargs, char **args)
{
	string topic = "topic";
	string address_pub = "ipc://frontend";
	string address_sub = "ipc://backend";

	for (int i = 0; i < nargs; i++){
		printf("args %i | value ' %s' | size %u\n", i, args[i], strlen(args[i]));
	}

	if(nargs < 1)
	{
		printf("usage must be defined as pub or sub\n");
		return -1;
	}
	else
	{
		if(args[1] == std::string("sub"))
		{
			printf("its a SUB \n");	
			Subscriber sub("test");
			string data;
			int rc = 0;

			rc = sub.Connect(address_sub);
			if(rc != 0) printf("[ERROR] Connect : %s\n", strerror(rc));

			rc = sub.Subscribe(topic);
			if(rc != 0) printf("[ERROR] Subscribe : %s\n", strerror(rc));

			for(int i = 0; i < 9; i++){ // With PUB we send 10 messages but ZMQ always miss the first so we wait for 9.
				rc = sub.ReceiveBlock(data);
				if(rc != 0) printf("[INFO] ReceiveBlock : %s\n", strerror(rc));
				printf("recv = %s\n", data.c_str());
				data.clear();
			}

			sub.~Subscriber();
			return 0;
		}
		else if(args[1] == std::string("pub"))
		{
			printf("its a PUB \n");
			Publisher pub("test");
			string data = "Data my data my data";
			int rc = 0;

			rc = pub.Bind(address_pub, true);

			for(int i = 1; i < 11; i++){
				rc = pub.Publish(topic, data);
				usleep(1000000);
			}

			pub.~Publisher();
			return 0;
		}
		else
		{
			printf("Wrong input\n");
			return -1;
		}
	}
}
