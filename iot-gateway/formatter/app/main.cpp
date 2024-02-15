#include "JsonFormatter.h"

using namespace std;

int main() {
    JsonFormatter formatter;

    formatter.addField("name", "John Doe");
    formatter.addField("age", 30);
    formatter.addField("city", "New York");
    formatter.addField("is_student", false);

    std::string jsonString = formatter.toString();

    std::cout << "JSON String:\n" << jsonString << std::endl;

    return 0;
}