/**
 * Function to get all the todos!
 */
function getAllTodos() {
  console.log("Getting all the todos.");

  get("/api/todos", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByStatus() {
  console.log("Getting all the todos.");

  get("/api/todos?status=" + document.getElementById("status").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
  }
  function getAllTodosByContainedString() {
    console.log("Getting all the todos.");

    get("/api/todos?contains=" + document.getElementById("body").value, function (returned_json) {
      document.getElementById('jsonDump').innerHTML = returned_json;
    });
  }



