package umm3601.Todo;

import org.junit.Test;
import umm3601.todo.Todo;
import umm3601.todo.TodoDatabase;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class TodoTests {
  @Test
  public void filterTodosByStatus() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] completeTodos = db.filterTodosByStatus(allTodos, true);
    assertEquals("Incorrect number of Todos that are complete", 143, completeTodos.length);

    Todo[] incompleteTodos = db.filterTodosByStatus(allTodos,false);
    assertEquals("Incorrect number of Todos that are incomplete", 157, incompleteTodos.length);
  }
  @Test
  public void totalUserCount() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());
    assertEquals("Incorrect total number of Todos", 300, allTodos.length);
  }

  @Test
  public void filterTodosByContains() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] containsCillum = db.getTodosFromString(allTodos, "Cillum");
    assertEquals("Incorrect number of Todos", 8, containsCillum.length);

    Todo[] containscillum2 = db.getTodosFromString(allTodos, "cillum");
    assertEquals("Incorrect number of Todos", 72, containscillum2.length);
  }

  @Test
  public void getLimitedTodos() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] limitedList = db.setMaxTodos(allTodos, 9);
    assertEquals("Incorrect number of Todos", 9, limitedList.length);

    Todo[] limitedList2 = db.setMaxTodos(allTodos, 69);
    assertEquals("Incorrect number of Todos", 69, limitedList2.length);
  }
  @Test
  public void getTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] containsFry = db.getTodosbyOwner(allTodos, "Fry");
    assertEquals("Incorrect number of Todos", 61, containsFry.length);

    Todo[] containsBlanche = db.getTodosbyOwner(allTodos, "Blanche");
    assertEquals("Incorrect number of Todos", 43, containsBlanche.length);
  }
  @Test
  public void getTodosByCategory() throws  IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] containsGroceries = db.getTodosbyCategory(allTodos, "groceries");
    assertEquals("Incorrect number of Todos", 76, containsGroceries.length);

    Todo[] containsHomework = db.getTodosbyCategory(allTodos, "homework");
    assertEquals("Incorrect number of Todos", 79, containsHomework.length);
  }
  @Test
  public void getFilteredTodos() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] filteredTodos = db.filterTodosByStatus(allTodos, true);
    assertEquals("Incorrect number of Todos that are complete", 143, filteredTodos.length);

    filteredTodos = db.getTodosFromString(filteredTodos, "ipsum");
    assertEquals("Incorrect number of Todos", 26, filteredTodos.length);

    filteredTodos = db.getTodosbyOwner(filteredTodos, "Fry");
    assertEquals("Incorrect number of Todos", 6, filteredTodos.length);

    filteredTodos = db.getTodosbyCategory(filteredTodos, "software design");
    assertEquals("Incorrect number of Todos", 4, filteredTodos.length);

    filteredTodos = db.setMaxTodos(filteredTodos, 3);
    assertEquals("Incorrect amount of Todos", 3, filteredTodos.length);
  }
}
