parse rawInput = map read (words rawInput) :: [Int]
day01p1 input = (length . filter id) $ zipWith (<) input (tail input)

main = interact (show . day01p1 . parse)