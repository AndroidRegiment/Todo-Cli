import java.io.File
import kotlin.system.exitProcess

private const val FILE_NAME = "todo.txt"

class FileHandler {

    private var file = File(FILE_NAME)
    private val taskList = mutableListOf<TodoItem>()
    private fun createFile(): Boolean = file.createNewFile()


    init {
        createFile()
        fillTaskList()
    }


    private fun addTask(todoItem: TodoItem) {
        file.appendText("${todoItem.isCompleted} ${todoItem.taskName}\n")
        taskList.add(todoItem)
    }

    private fun resetData() {
        file.writeText("")
        taskList.clear()
    }

    private fun exitApplication() {
        exitProcess(1)
    }

    private fun showItem() {
        if(taskList.isEmpty()) {
            println("No Task Found.")
            return
        }
        var index = 1
        taskList.forEach { task ->
            val taskName = if(task.isCompleted){
                "\u001B[9m ${task.taskName} \u001B[0m"
            }else{
                task.taskName
            }
            println("$index. $taskName")
            index++
        }
    }

    private fun markAsComplete(index: Int) {
        taskList[index] = taskList[index].copy(isCompleted = true)
        rewriteFileFromList()
    }

    private fun fillTaskList() {
        file.forEachLine { line ->
            val isComplete: Boolean = line.subSequence(startIndex = 0, endIndex = 5).toString().trim().toBoolean()
            val itemName: String = line.subSequence(startIndex = 5, endIndex = line.length).toString().trim()
            taskList.add(TodoItem(taskName = itemName, isCompleted = isComplete))
        }
    }

    private fun removeTask(index: Int) {
        taskList.removeAt(index)
        rewriteFileFromList()
    }

    private fun rewriteFileFromList() {
        file.writeText("")
        taskList.forEach { todoItem ->
            file.appendText("${todoItem.isCompleted} ${todoItem.taskName}\n")
        }
    }


//public function

    fun add(task: String) {
        if (task == "") {
            println("Error : task can't be empty.")
            return
        }
        addTask(TodoItem(taskName = task))
    }

    fun done(index: String) {
        val indexValue = index.convertToInt()
        indexValue?.let { it ->
            if (1 <= it && it <= taskList.size) {
                markAsComplete(it - 1)
            } else {
                println("ERROR :- Give an valid index")
            }
        }
    }

    fun rm(index: String) {
        val indexValue = index.convertToInt()
        indexValue?.let { it ->
            if (1 <= it && it <= taskList.size) {
                removeTask(it - 1)
            } else {
                println("ERROR :- Give an valid index")
            }
        }
    }

    fun reset() {
        resetData()
    }

    fun list() {
        showItem()
    }

    fun help() {
        println(TODO_HELP.trimMargin())
    }

    fun exit() {
        exitApplication()
    }

}

