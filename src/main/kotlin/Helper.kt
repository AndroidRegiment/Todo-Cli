import java.util.*


const val TODO_HELP = """
    |Available Commands 
    |   add [TASK]
    |       add new task
    |       Usage:  add \"complete project\"
    |   list
    |       lists all tasks
    |       Example: todo list
    |   done [INDEX]
    |       marks task as done
    |       Example: todo done 2 3 (marks second and third tasks as completed)
    |   rm [INDEX]
    |       removes a task
    |       Example: todo rm 4
    |   reset
    |       deletes all tasks
    |   help
    |       list all the available commands
    |       Example: help
    |   sort
    |       sorts completed and uncompleted tasks
    |       Example: sort
    |   exit
    |       close the application
    |       Example: exit
"""

fun String.convertToInt() : Int?{
    return try {
        this.toInt()
    }catch (e : Exception){
        println("ERROR : Pass Numeric value for index.")
        null
    }
}



class Helper {
    private val fileHandler = FileHandler()

    fun getCommand(): Pair<String, String> {
        val scanner = Scanner(System.`in`)
        print("todo :- ")
        val command = scanner.next().trim()
        val argument = scanner.nextLine().trim()

        return Pair(command, argument)
    }

    fun executeCommand(command: String, argument: String) {
        when (command) {
            "add" -> {
                fileHandler.add(argument)
            }
            "done" -> {
                fileHandler.done(argument)
            }
            "list" -> {
                fileHandler.list()
            }
            "rm" -> {
                fileHandler.rm(argument)
            }
            "reset" -> {
                fileHandler.reset()
            }
            "exit" -> {
                fileHandler.exit()
            }
            "help" -> {
                fileHandler.help()
            }
            "sort" -> {
                fileHandler.sort()
            }
            else -> {
                println("Error : \'$command\' is not a todo command. See \'help\'.")
            }
        }
    }
}

























