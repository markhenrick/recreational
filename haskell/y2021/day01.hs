day01p1 input = (length . filter id) $ zipWith (<) input (tail input)
zipPlus = zipWith (+)
intoWindows input = zipPlus (zipPlus input (tail input)) (tail (tail input))
day01p2 = day01p1 . intoWindows

main = do
    rawInput <- getContents
    let input = map read (words rawInput) :: [Int]
    print (day01p1 input)
    print (day01p2 input)