#ifndef QUARKUS_CLIENT_H
#define QUARKUS_CLIENT_H

#include <string>
#include <ostream>
#include <iostream>
#include <curl/curl.h>
#include "unistd.h"

class QuarkusClient {
public:
    QuarkusClient();
    ~QuarkusClient();

    std::string get(const std::string& url);
    std::string post(const std::string& url, const std::string& data);

private:
    static size_t WriteCallback(void* contents, size_t size, size_t nmemb, std::string* output);

private:
    CURL* curl;
};

#endif //QUARKUS_CLIENT_H
