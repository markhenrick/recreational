package site.markhenrick.adventofcode.y2020;

import site.markhenrick.adventofcode.common.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

class Day08 {
	public static int solvePart1(final String instructions) {
		return new VM(StringUtil.LINE_SPLITTER.apply(instructions).collect(Collectors.toList())).destructiveRun();
	}

	private static class VM {
		private final List<String> instructions;
		int accumulator = 0;
		int pc = 0;

		VM(final List<String> instructions) {
			this.instructions = instructions;
		}

		private void execute(final String input) {
			final var opcode = input.substring(0, 3);
			switch (opcode) {
				case "nop":
					return;
				case "acc":
					accumulator += decodeOperand(input);
					return;
				case "jmp":
					pc += decodeOperand(input) - 1;
					return;
				default:
					throw new AssertionError(opcode);
			}
		}

		private static int decodeOperand(final String input) {
			return Integer.parseInt(input.substring(5)) * (input.charAt(4) == '+' ? 1 : -1);
		}

		int destructiveRun() {
			String instruction;
			do {
				instruction = instructions.get(pc);
				if (instruction != null) {
					instructions.set(pc, null);
					pc++;
					execute(instruction);
				}
			} while (instruction != null);
			return accumulator;
		}
	}
}
