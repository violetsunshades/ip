# Navis User Guide

Navis is a **command-line task management chatbot** that helps you manage your tasks efficiently.

With Navis, you can add tasks, track deadlines, manage events, mark tasks as completed, search tasks, and store tasks automatically.

---

# Quick Start

1. Make sure **Java 17** is installed on your computer.

2. Download the latest `ip.jar` from the project's GitHub Release page.

3. Open a terminal and navigate to the folder containing the jar file.

4. Run the program:

```
java -jar ip.jar
```

You should see:

```
Hello! I'm Navis
What can I do for you?
```

You can now begin entering commands.

---

# Features

## Add a Todo

Adds a task without a deadline.

Command:

```
todo DESCRIPTION
```

Example:

```
todo read book
```

---

## Add a Deadline

Adds a task with a deadline.

Command:

```
deadline DESCRIPTION /by DATE
```

Example:

```
deadline assignment /by 2026-04-01
```

---

## Add an Event

Adds a task with a start and end time.

Command:

```
event DESCRIPTION /from START /to END
```

Example:

```
event meeting /from 2pm /to 4pm
```

---

## List Tasks

Displays all tasks.

Command:

```
list
```

Example output:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] assignment (by: 2026-04-01)
3. [E][ ] meeting (from: 2pm to: 4pm)
```

---

## Mark Task as Done

Marks a task as completed.

Command:

```
mark INDEX
```

Example:

```
mark 1
```

---

## Unmark Task

Marks a task as not completed.

Command:

```
unmark INDEX
```

Example:

```
unmark 1
```

---

## Delete Task

Deletes a task from the list.

Command:

```
delete INDEX
```

Example:

```
delete 2
```

---

## Find Tasks

Finds tasks containing a keyword.

Command:

```
find KEYWORD
```

Example:

```
find book
```

---

## Exit Program

Exits Navis.

Command:

```
bye
```

---

# Data Storage

Navis automatically saves tasks after each command.

Saved tasks will be loaded automatically the next time the program runs.

---

# Command Summary

| Command | Description | Example |
|---------|-------------|---------|
| todo | Add a todo task | `todo read book` |
| deadline | Add deadline task | `deadline assignment /by 2026-04-01` |
| event | Add event task | `event meeting /from 2pm /to 4pm` |
| list | Display tasks | `list` |
| mark | Mark task as done | `mark 1` |
| unmark | Mark task as not done | `unmark 1` |
| delete | Delete task | `delete 2` |
| find | Search tasks | `find book` |
| bye | Exit program | `bye` |

---

# Notes

* Task indexes correspond to the numbers shown in the task list.
* Commands are case-sensitive.
* Tasks are automatically saved.
