<?php
    require_once __DIR__."/../../autoload/autoload.php";
    $open = "monan";
    $loaimonan = $db->fetchAll("loaimonan");
    $quan_an = $db->fetchAll("quan_an");

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
    	$data = [
    		"maloai" => postInput("maloai"),
    		"ma_qa" => postInput("ma_qa"),
    		"tenmonan" => postInput("tenmonan"),
    		"gia" => postInput("gia"),
    		"hinhanhmonan" => postInput("hinhanhmonan")
    		];
    	$error =[];

    	if (postInput("tenmonan")== '') {
    		$error['tenmonan'] = "Vui lòng nhập tên món!";
    	}

    	if (empty($error)) {
    		$insert = $db->insert("monan",$data);
    		// print_r($insert);
    		if ($insert > 0) {
    			$_SESSION['success'] =  "Thêm mới thành công!";
    			redirectAdmin("monan");
    		} else {
    			// thêm thất bại
    			$_SESSION['error'] =  "Thêm mới thất bại!";
    			redirectAdmin("monan");
    		}
    	}
    }

?>

<?php require_once __DIR__."/../../layouts/hearder.php"; ?>
<div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item active">
            Home
          </li>
          <li class="breadcrumb-item active">Món ăn</li>
          <li class="breadcrumb-item"><a href="#">Thêm món</a></li>
        </ol>
      </div>

      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn " href="add.php">Thêm mới</a></div>
          <div class="card-body">
          	<div class="card-header"><h2>Thêm mới món ăn</h2></div>
		    <form class="form-horizontal" action="" method="POST">
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="email">Mã món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control " id="disabledInput" placeholder="Nhập mã món ăn" disabled>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Mã loại:</label>
		            <div class="col-sm-10">
		            	<select class="form-control" name="maloai" >
		            		<option value="">- Chọn loại món ăn -</option>
		            		<?php foreach ($loaimonan as $item): ?>
		            		<option value="<?php echo $item['maloai'] ?>"><?php echo $item['tenloai'] ?></option>
		            		<?php endforeach ?>
		            	</select>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Mã quán ăn:</label>
		            <div class="col-sm-10">
		                <select class="form-control" name="ma_qa" >
		            		<option value="">- Chọn quán ăn ăn -</option>
		            		<?php foreach ($quan_an as $item): ?>
		            		<option value="<?php echo $item['ma_qa'] ?>"><?php echo $item['ten_qa'] ?></option>
		            		<?php endforeach ?>
		            	</select>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Tên món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="tenmonan" placeholder="Nhập tên món ăn">
		                <?php if (isset($error['tenmonan'])): ?>
		                	<p class="text-danger"> <?php echo $error['tenmonan'] ?></p>
		                <?php endif ?>
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Giá món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="gia" placeholder="Nhập giá món ăn">
		            </div>
		        </div>
		        <div class="form-group">
		            <label class="control-label col-sm-2" for="pwd">Hình ảnh món ăn:</label>
		            <div class="col-sm-10">
		                <input type="text" class="form-control" name="hinhanhmonan" placeholder="Nhập url ảnh món ăn">
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">
		                <button type="submit" class="btn btn-success">Thêm</button>
		            </div>
		        </div>
		    </form>
          </div>
        </div>


<?php require_once __DIR__. "/../../layouts/footer.php"; ?>