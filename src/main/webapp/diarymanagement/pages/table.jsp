<html>
  <head>
    <script type='text/javascript' src='js/jquery-1.3.2.js'></script>
    <script type='text/javascript'>
$(document).ready(
  function() {
    $('input#tmpEmptyTable').click(
      function($e) {
        $e.preventDefault();
        $('td').empty();
      }
    );

    $('input#tmpDelete').click(
      function($e) {
        $e.preventDefault();
        $('h4, table').remove();
      }
    );
  }
);
    </script>

  </head>
  <body>
     <h4>C Albums</h4>
     <table>
       <thead>
         <tr>
           <th>Title</th>
           <th>Year</th>        
         </tr>
       </thead>
       <tbody>
         <tr>
           <td>D</td>
           <td>1980</td>
         </tr>
       </tbody>
     </table>
     <input type='submit' id='tmpEmptyTable' value='Empty Table' />
     <input type='submit' id='tmpDelete' value='Delete Content' />
  </body>
</html>