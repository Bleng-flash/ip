# Balloon User Guide

![Image of the UI of the Balloon chatbot](./Ui.png)

**Balloon** is a chatbot for you to **manage your tasks**, optimised for
those familiar with a Command Line Interface(CLI). \
There are 3 types of tasks supported: Todo, Deadline, and Event. \

## Features
1. [List](#list-all-tasks)
2. [Add Todo](#add-a-todo-task)
3. [Add Deadline](#add-a-deadline-task)
4. [Add Event](#add-an-event-task)
4. [Delete Task](#delete-a-task)
5. [Mark Task as Done](#mark-a-task-as-done)
6. [Mark Task as Undone](#mark-a-task-as-undone)
7. [Find Task](#find-tasks-by-keyword)
8. [Undo](#undo-previous-command)
9. [Exit](#exit)


### List all tasks
Shows a list of all tasks.\
Format: `list`

### Add a Todo task
Adds a Todo task to the end of the list of tasks. \
Format: `todo <description>`

### Add a Deadline task
Adds a Deadline task to the end of the list of tasks. \
Format: `deadline <description> /by <date/time>`
- The date/time fields can be interpreted in 3 ways:
    1. Date format
        - Input is of the form "yyyy--MM--dd" and represents a valid Date
        - When displayed (using `list`), the output will be shown as "MMM dd yyyy"
    2. Date-Time format
        - Input is of the form "yyyy--MM--dd HHmm" and represents a valid Date-Time
        - When displayed (using `list`), the output will be shown as "MMM dd yyyy HH:mm"
    3. Raw string
        - If the time/date field cannot be interpreted using either
          of the above 2 methods, then this is the default
        - When displayed (using `list), the output will be the same as
          the raw input string

### Add an Event task
Adds an Event task to the end of the list of tasks. \
Format: `event <description> /from <date/time> /to <date/time>`
- The date/time fields can be interpreted in 3 ways:
    1. Date format
        - Input is of the form "yyyy--MM--dd" and represents a valid Date
        - When displayed (using `list`), the output will be shown as "MMM dd yyyy"
    2. Date-Time format
        - Input is of the form "yyyy--MM--dd HHmm" and represents a valid Date-Time
        - When displayed (using `list`), the output will be shown as "MMM dd yyyy HH:mm"
    3. Raw string
        - If the time/date field cannot be interpreted using either
          of the above 2 methods, then this is the default
        - When displayed (using `list), the output will be the same as
          the raw input string

### Delete a task
Deletes the task specified by the given task number. \
Format: `delete <task-number>`
- task-number must be a positive integer smaller than or equal to the number of tasks in the list

Example: `delete 2` removes the second task on the list

### Mark a task as Done
Marks the task specified by the given task number as done. \
A task that is marked as done will be indicated with a 'X' when `list` is executed. \
Format: `mark <task-number>`
- task-number must be a positive integer smaller than or equal to the number of tasks in the list

### Mark a task as Undone
Marks the task specified by the given task number as not done. \
Format: `unmark <task-number>`
- task-number must be a positive integer smaller than or equal to the number of tasks in the list

### Find tasks by Keyword
Shows all the task in the list whose _description_ **contains** the given keyword. \
Note that string-matching is **case-sensitive** here. \
Format: `find <keyword>`

### Undo previous command
Undoes the previous command that was _successfully_ executed.
The undo command can be used **up to 1 time consecutively** only . \
This command only works if the previous command is one of the following:
- `todo`
- `deadline`
- `event`
- `mark`
- `unmark`
- `delete`

Format: `undo`

Example: `todo sometask` followed by `undo` means that the
new Todo task added will be removed (thus undoing the todo command)

### Exit
Terminates and leaves the application. \
Format:`bye`
