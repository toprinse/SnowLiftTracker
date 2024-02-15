#ifndef JSON_FORMATTER_H
#define JSON_FORMATTER_H

#include <iostream>
#include <sstream>
#include <vector>

class JsonFormatter {
public:
    JsonFormatter();

    template <typename T>
    void addField(const std::string& name, const T& value);
    
    void addField(const std::string& name, const char* value);

    std::string toString() const;

private:
    std::vector<std::pair<std::string, std::string>> fields;
    std::stringstream jsonStream;
};


#endif