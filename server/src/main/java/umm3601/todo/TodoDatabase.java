package umm3601.todo;

import com.google.gson.Gson;
import umm3601.todo.Todo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of user info
 * <p>
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id == id).findFirst().orElse(null);
  }

 public Todo[] getTodosFromString(Todo[] todos, String target) {
   int x = todos.length;
   Todo[] newTodo = new Todo[x];
   int c = 0;
   for (int i = 0; i < x; i++) {
     if (todos[i].body.contains(target)) {
       newTodo[c] = todos[i];
       c = c+1;
     }
   }
   Todo[] targetTodo = new Todo[c];
    for (int i = 0; i < c; i++) {
      targetTodo[i] = newTodo[i];
    }
    return targetTodo;
   }
  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter age if defined
    if (queryParams.containsKey("status")) {

      if (queryParams.get("status") [0].equals("complete")) {
        boolean targetStatus = true;
        //boolean targetStatus = Boolean.parseBoolean(queryParams.get("status") [0]);
        filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
      } else {
        if (queryParams.get("status") [0].equals("incomplete")) {
          boolean targetStatus = false;
          filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
        }
          else {
            if (!(queryParams.get("status") [0].equals(""))) {
             filteredTodos = null;
           }
          }
         }
      }
    if (queryParams.containsKey("contains")) {
      if (queryParams.get("contains") [0] != "") {
        if (filteredTodos != null) {
          String targetString = queryParams.get("contains")[0];
          Todo[] targetTodos = getTodosFromString(filteredTodos, targetString);
          filteredTodos = targetTodos;
        }
      }
    }
    if (queryParams.containsKey("owner")) {
      if (queryParams.get("owner") [0] != "") {
        if (filteredTodos != null) {
          String targetOwner = queryParams.get("owner") [0];
          filteredTodos = getTodosbyOwner(filteredTodos, targetOwner);
        }
      }
    }
    if (queryParams.containsKey("category")) {
      if (queryParams.get("category") [0] != "") {
        if (filteredTodos != null) {
          String targetCategory = queryParams.get("category") [0];
          filteredTodos = getTodosbyCategory(filteredTodos, targetCategory);
        }
      }
    }

    if (queryParams.containsKey("limit")) {
      if (!(queryParams.get("limit") [0].equals(""))) {
        if (filteredTodos != null) {
          int targetLimit = Integer.parseInt((queryParams.get("limit")[0]));
          Todo[] targetTodos = setMaxTodos(filteredTodos, targetLimit);
          filteredTodos = targetTodos;
        }
      }
    }


    // Process other query parameters here...

    return filteredTodos;
  }

  /**
   * Get an array of all the users having the target age.
   *
   * @param todos    the list of users to filter by age
   * @param targetStatus the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */

  public Todo[] filterTodosByStatus(Todo[] todos, boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(Todo[]::new);
  }

  public Todo[] getTodosbyOwner (Todo[] todos, String target) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(target)).toArray(Todo[]::new);
  }

  public Todo[] getTodosbyCategory (Todo[] todos, String target) {
    return Arrays.stream(todos).filter(x -> x.category.equals(target)).toArray(Todo[]::new);
  }

  public Todo[] setMaxTodos(Todo[] todos, int x) {
    Todo[] maxTodo = new Todo[x];
    int c = 0;

    for (int i = 0; i < x; i++) {
      Todo[] targetTodo = new Todo[c];
      if (i ==todos.length) {
       for (int k = 0; k < c; k++) {
         targetTodo[k] = maxTodo[k];
       }
        return targetTodo;
      } if (todos[i] != null) {
        maxTodo[i] = todos[i];
        c = c + 1;
      }
    }
    return maxTodo;
  }
}
