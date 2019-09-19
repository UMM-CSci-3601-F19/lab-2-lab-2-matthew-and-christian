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

function getTodosByLimit() {
  console.log("Getting all the todos.");

  get("/api/todos?limit=" + document.getElementById("limit").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getTodosByOwner() {
  console.log("Getting all the todos.");

  get("/api/todos?owner=" + document.getElementById("owner").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

  function getAllTodosByContainedString() {
    console.log("Getting all the todos.");

    get("/api/todos?contains=" + document.getElementById("contains").value, function (returned_json) {
      document.getElementById('jsonDump').innerHTML = returned_json;
    });
  }
    function getAllTodosByCategory() {
      console.log("Getting all the todos.");

      get("/api/todos?category=" + document.getElementById("category").value, function (returned_json) {
        document.getElementById('jsonDump').innerHTML = returned_json;
      });
  }
    function getAllFilteredTodos() {
      console.log("Getting all the todos.");

      get("/api/todos?status=" + document.getElementById("status").value
        + "&contains=" + document.getElementById("contains").value
        + "&limit=" + document.getElementById("limit").value
        + "&owner=" + document.getElementById("owner").value
        + "&category=" + document.getElementById("category").value,
        function (returned_json) {
        document.getElementById('jsonDump').innerHTML = returned_json;
      });
    }




