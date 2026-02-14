#include <iostream>
#include <vector>
#include <fstream>
#include <sstream>

using namespace std;

class Task
{
public:
    string title;
    string category;
    bool completed;

    Task(string t, string c, bool comp = false)
    {
        title = t;
        category = c;
        completed = comp;
    }

    string toFileString() const
    {
        return title + "|" + category + "|" + (completed ? "1" : "0");
    }

    static Task fromFileString(string line)
    {
        stringstream ss(line);
        string title, category, status;

        getline(ss, title, '|');
        getline(ss, category, '|');
        getline(ss, status, '|');

        return Task(title, category, status == "1");
    }
};

class TaskManager
{
private:
    vector<Task> tasks;
    string filename = "tasks.txt";

public:
    TaskManager()
    {
        loadTasks();
    }

    void addTask()
    {
        string title, category;

        cout << "Enter task title: ";
        getline(cin, title);

        cout << "Enter category: ";
        getline(cin, category);

        tasks.push_back(Task(title, category));
        saveTasks();

        cout << "Task added successfully!\n";
    }

    void markTaskCompleted()
    {
        viewTasks(false);

        cout << "Enter task number to mark completed: ";
        int index;
        cin >> index;
        cin.ignore();

        if (index >= 0 && index < tasks.size())
        {
            tasks[index].completed = true;
            saveTasks();
            cout << "Task marked as completed!\n";
        }
        else
        {
            cout << "Invalid task number!\n";
        }
    }

    void viewTasks(bool completedStatus)
    {
        cout << "\n";
        for (int i = 0; i < tasks.size(); i++)
        {
            if (tasks[i].completed == completedStatus)
            {
                cout << i << ". "
                     << tasks[i].title
                     << " | Category: "
                     << tasks[i].category
                     << "\n";
            }
        }
        cout << "\n";
    }

    void saveTasks()
    {
        ofstream file(filename);
        for (const Task &task : tasks)
        {
            file << task.toFileString() << endl;
        }
        file.close();
    }

    void loadTasks()
    {
        ifstream file(filename);
        string line;

        while (getline(file, line))
        {
            tasks.push_back(Task::fromFileString(line));
        }

        file.close();
    }
};

int main()
{
    TaskManager manager;
    int choice;

    while (true)
    {
        cout << "\n===== TO-DO LIST MENU =====\n";
        cout << "1. Add Task\n";
        cout << "2. Mark Task as Completed\n";
        cout << "3. View Pending Tasks\n";
        cout << "4. View Completed Tasks\n";
        cout << "5. Exit\n";
        cout << "Choose option: ";

        cin >> choice;
        cin.ignore();

        switch (choice)
        {
        case 1:
            manager.addTask();
            break;
        case 2:
            manager.markTaskCompleted();
            break;
        case 3:
            cout << "Pending Tasks:\n";
            manager.viewTasks(false);
            break;
        case 4:
            cout << "Completed Tasks:\n";
            manager.viewTasks(true);
            break;
        case 5:
            cout << "Goodbye!\n";
            return 0;
        default:
            cout << "Invalid option!\n";
        }
    }
}
