#include <stdio.h>
#include <stdlib.h>
#include "../ioutils.h"

int part1(int *input, int inputSize) {
    int count = 0;
    for (int i = 0; i < inputSize - 1; i++) {
        count += !!(input[i] < input[i+1]);
    }
    return count;
}

// TODO this can overflow...
int *intoWindows(int *input, int inputSize) {
    int *windows = (int*) malloc((inputSize - 2) * (sizeof (int)));
    for (int i = 0; i < inputSize - 2; i++) {
        windows[i] = input[i] + input[i + 1] + input[i + 2];
    }
    return windows;
}

int part2(int *input, int inputSize) {
    int *windows = intoWindows(input, inputSize);
    int result = part1(windows, inputSize - 2);
    free(windows);
    return result;
}

int main(int argc, char **argv) {
    int inputSize = 0;
    int *input = readInput(&inputSize);
    printf("Part 1: %d\n", part1(input, inputSize));
    printf("Part 2: %d\n", part2(input, inputSize));
    free(input);
    return 0;
}