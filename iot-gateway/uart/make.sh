#!/bin/bash

echo uart_receiver
g++ -Wall src/uart.c -o bin/uart_receiver -O0 -g
echo uart_sender
g++ -Wall src/uart_sender.c -o bin/uart_sender -O0 -g


