package site.markhenrick.adventofcode.y2015;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Predicate;

public class Day04 {
	static int search(String key, Predicate<byte[]> predicate) throws NoSuchAlgorithmException {
		var digest = MessageDigest.getInstance("MD5");
		for (int i = 0; true; i++) {
			var string = key + i;
			var hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
			if (predicate.test(hash)) {
				return i;
			}
		}
	}

	static int part1(String key) throws NoSuchAlgorithmException {
		return search(key, hash -> hash[0] == 0x00 && hash[1] == 0x00 && hash[2] > 0 && hash[2] < 0x10);
	}

	static int part2(String key) throws NoSuchAlgorithmException {
		return search(key, hash -> hash[0] == 0x00 && hash[1] == 0x00 && hash[2] == 0x00);
	}
}
