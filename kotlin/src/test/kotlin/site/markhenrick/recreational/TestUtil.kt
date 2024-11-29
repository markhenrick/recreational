package site.markhenrick.recreational

private class DummyClass

fun getChallengeInput(year: Int, day: Int) =
    DummyClass::class.java.getResource("/inputs/$year/${String.format("%02d", day)}.txt")!!.readText().trim()