#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>

const int INPUT_BUFFER_SIZE = 4096; // ints
const int MAX_NUMBER_LENGTH = 10; // 4294967296 = 10 chars

// probably already in the stdlib
void fill(char *array, size_t size, char value) {
    for (size_t i = 0; i < size; i++) {
        array[i] = value;
    }
}

// TODO use fscanf instead
int *readInput(int *outputSize) {
    int *buffer = (int*) malloc(INPUT_BUFFER_SIZE * (sizeof (int)));
    char singleNumberBuffer[MAX_NUMBER_LENGTH + 1];
    fill(singleNumberBuffer, MAX_NUMBER_LENGTH + 1, '\0');
    int bufferIndex = 0;
    int numberIndex = 0;
    char c;
    while ((c = getchar()) != EOF) {
        if (c == '\n' || c == ' ') {
            int number = atoi(singleNumberBuffer);
            buffer[bufferIndex++] = number;
            fill(singleNumberBuffer, MAX_NUMBER_LENGTH + 1, '\0');
            numberIndex = 0;
        } else {
            assert(numberIndex < MAX_NUMBER_LENGTH);
            singleNumberBuffer[numberIndex++] = c;
        }
    }
    *outputSize = bufferIndex;
    return buffer;
}
