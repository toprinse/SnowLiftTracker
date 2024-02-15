#include "JsonFormatter.h"

JsonFormatter::JsonFormatter() {
    jsonStream << "{";
}

template <typename T>
void JsonFormatter::addField(const std::string& name, const T& value) {
    fields.push_back({name, std::to_string(value)});
}

template void JsonFormatter::addField<int>(const std::string& name, const int& value);
template void JsonFormatter::addField<double>(const std::string& name, const double& value);
template void JsonFormatter::addField<float>(const std::string& name, const float& value);
template void JsonFormatter::addField<bool>(const std::string& name, const bool& value);

// Specialization for char arrays
void JsonFormatter::addField(const std::string& name, const char* value) {
    fields.push_back({name, value});
}



std::string JsonFormatter::toString() const {
    std::stringstream json;
    json << "{";
    for (size_t i = 0; i < fields.size(); ++i) {
        const auto& entry = fields[i];
        const std::string& name = entry.first;
        const std::string& value = entry.second;

        json << "\"" << name << "\": \"" << value << "\"";
        if (i < fields.size() - 1) {
            json << ",";
        }
    }
    json << "}";
    return json.str();
}

