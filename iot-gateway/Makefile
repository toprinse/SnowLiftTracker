BUILD_DIR=build/
BUILD_BIN_DIR=${BUILD_DIR}bin/
BUILD_LIB_DIR=${BUILD_DIR}lib/

# Project files & dependencies
SERVICE_DIR= services/
LIB_DIR=${SERVICE_DIR}lib/
SRC_DIR=${SERVICE_DIR}src/

SUBDIRS := $(wildcard */)
SUBDIRS := $(filter-out $(SERVICE_DIR) $(BUILD_DIR), $(SUBDIRS))

ZMQ_DIR := zmq/
REST_DIR := rest/
UART_DIR := uart/
FORMATTER_DIR := formatter/

# Iterate over each directory in SUBDIRS and find header files in the lib/ subdirectory.
# Append the list of header files to the HEADER_LIST variable.
HEADER_LIST :=            # Initialize HEADER_LIST variable
$(foreach OBJECT,$(SUBDIRS), \
    $(eval HEADER_LIST += $(shell find $(OBJECT)lib -type f -name '*.h')))
                          # For each directory, evaluate the find command and append the result
                          # to HEADER_LIST. The eval function is used to perform this evaluation
                          # during Makefile parsing.

SHARED_LIB_LIST:=        
$(foreach OBJECT,$(SUBDIRS), \
    $(eval SHARED_LIB_LIST += $(shell find $(OBJECT)$(BUILD_LIB_DIR) -type f -name '*.so')))


# Compiler, archiver & linker options
COMPILER=${CXX}
COMPILER_FLAGS= -O2 -Wall  -std=c++11 
LD_FLAGS= -lpthread -lzmq -lczmq
LIB_INCLUDES=-I${LIB_DIR}

# Executables
LISTENER_NAME := uartlistener
CONNECTOR_NAME := quarkusconnector
LIB_LISTENER := -L${BUILD_LIB_DIR} -lsltuart -lsltipczmq 
LIB_CONNECTOR :=  -L${BUILD_LIB_DIR} -lsltserver -lsltipczmq -lsltformatter 
LISTENER_EXEC := ${BUILD_BIN_DIR}${LISTENER_NAME}
CONNECTOR_EXEC := ${BUILD_BIN_DIR}${CONNECTOR_NAME}

all: dir subdirs copy_header copy_shared_lib copy_proxy listener_exec connector_exec

# rule to make every sub dirs
subdirs: $(SUBDIRS)
		$(MAKE) -C $(ZMQ_DIR) all
		$(MAKE) -C $(REST_DIR) all
		$(MAKE) -C $(UART_DIR) all 
		$(MAKE) -C $(FORMATTER_DIR) all

copy_header: $(HEADER_LIST)
		cp $^ ${LIB_DIR}

copy_shared_lib: dir subdirs
		cp $(ZMQ_DIR)$(BUILD_LIB_DIR)*.so $(REST_DIR)$(BUILD_LIB_DIR)*.so $(UART_DIR)$(BUILD_LIB_DIR)*.so $(FORMATTER_DIR)$(BUILD_LIB_DIR)*.so  $(BUILD_LIB_DIR)

copy_proxy:
		cp $(ZMQ_DIR)$(BUILD_BIN_DIR)proxy $(BUILD_BIN_DIR)

listener_exec: ${LISTENER_EXEC}
${LISTENER_EXEC}: ${SRC_DIR}/%.cpp: | $(dir ${LISTENER_EXEC})
	${COMPILER} ${COMPILER_FLAGS} $(LIB_INCLUDES) ${SRC_DIR}main.cpp -o $@ ${LD_FLAGS} ${LIB_LISTENER}

connector_exec: ${CONNECTOR_EXEC}
${CONNECTOR_EXEC}: ${SRC_DIR}/%.cpp: | $(dir ${CONNECTOR_EXEC})
	${COMPILER} ${COMPILER_FLAGS} $(LIB_INCLUDES) ${SRC_DIR}main2.cpp -o $@ ${LD_FLAGS} ${LIB_CONNECTOR}

.PHONY: all clean info copy_header copy_shared_lib clean_all 

# Rule to create directories
dir:
	mkdir -p ${BUILD_BIN_DIR} ${BUILD_LIB_DIR} ${LIB_DIR}

clean:
	-@rm -rf ${BUILD_DIR}
	-@rm -f ${LIB_DIR}/*.h 

clean_all:
	-@rm -rf ${BUILD_DIR}
	-@rm -f ${LIB_DIR}/*.h	
	$(MAKE) -C $(ZMQ_DIR) clean
	$(MAKE) -C $(REST_DIR) clean
	$(MAKE) -C $(UART_DIR) clean
	$(MAKE) -C $(FORMATTER_DIR) clean

info:
	@echo "[*] Application dir: ${APP_DIR}     "
	@echo "[*] Object dir:      ${OBJ_DIR}     "
	@echo "[*] Include dir:     ${INC_DIR}     "
	@echo "[*] Sources:         ${SRC}         "
	@echo "[*] Objects:         ${OBJECTS}     "
	@echo "[*] Dependencies:    ${DEPENDENCIES}"
