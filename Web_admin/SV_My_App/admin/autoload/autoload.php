<?php
  session_start();
  require_once __DIR__. "/../library/database.php";
  require_once __DIR__. "/../library/function.php";
  $db = new database;

  if (! isset($_SESSION['admin_id'])) {
  	header("location: /login");
  }
?>