
fun main(args: Array<String>) {
    val helper = Helper()
    println("Welcome to Todo cli. Use \'list\' command to see your tasks.")
    while (true) {
        val (command, argument) = helper.getCommand()
        helper.executeCommand(command = command, argument = argument)

    }
}

