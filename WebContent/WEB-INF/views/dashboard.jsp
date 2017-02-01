<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js"></script>
<script
	src="https://rawgit.com/mar10/jquery-ui-contextmenu/master/jquery.ui-contextmenu.js"></script>
<style>
 .hasmenu2 {
	border: 1px solid #008;
	margin: 3px;
	padding: 5px;
	width: 30px;
}

/* Optionally define a fixed width for menus */
.ui-menu {
	width: 220px;
}
/* Allow to use <kbd> elements inside the title to define shortcut hints. */
.ui-menu kbd {
	padding-left: 1em;
	float: right;
}

/* Define a custom icon */
.ui-icon.custom-icon-firefox {
	background-image: url(application_firefox.gif);
	background-position: 0 0;
}


</style>
<style>
.tree {
    min-height:20px;
    padding:19px;
    margin-bottom:20px;
    background-color:#fbfbfb;
    border:1px solid #999;
    -webkit-border-radius:4px;
    -moz-border-radius:4px;
    border-radius:4px;
    -webkit-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    -moz-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05)
}
.tree li {
    list-style-type:none;
    margin:0;
    padding:10px 5px 0 5px;
    position:relative
}
.tree li::before, .tree li::after {
    content:'';
    left:-20px;
    position:absolute;
    right:auto
}
.tree li::before {
    border-left:1px solid #999;
    bottom:50px;
    height:100%;
    top:0;
    width:1px
}
.tree li::after {
    border-top:1px solid #999;
    height:20px;
    top:25px;
    width:25px
}
.tree li span {
    -moz-border-radius:5px;
    -webkit-border-radius:5px;
    border:1px solid #999;
    border-radius:5px;
    display:inline-block;
    padding:3px 8px;
    text-decoration:none
}
.tree li.parent_li>span {
    cursor:pointer
}
.tree>ul>li::before, .tree>ul>li::after {
    border:0
}
.tree li:last-child::before {
    height:30px
}
.tree li.parent_li>span:hover, .tree li.parent_li>span:hover+ul li span {
    background:#eee;
    border:1px solid #94a0b4;
    color:#000
}
</style>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 450px
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		/* height: auto; */
		padding: 15px;
	}
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Logo</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Projects</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid ">
		<div class="row content" style="height: 600px">
			<div class="col-md-3 sidenav">
				<div class="well">				
					<p>My Projects</p>
				</div>
<div class="tree">
    <ul id=project_list>
        <li>
            <span class="hasmenu">Project 1</span>
            <ul>
                <li>
                	<span><i class="icon-minus-sign"></i> Test Case Suite 1</span>
                    <ul>
                        <li>
	                        <span><i class="icon-leaf"></i> Test Case1</span>
                        </li>
                    </ul>
                </li>
                <li>
                	<span><i class="icon-minus-sign"></i> Child</span> <a href="">Goes somewhere</a>
                 
                </li>
            </ul>
        </li>
    
    </ul>
</div>


	

			</div>
			<div class="col-sm-7">
				<div class="container-fluid">
					<div class="well">
						<button class="btn btn-primary" href="#modal" data-toggle="modal">Create</button>


						<button class="btn btn-success">Save</button>
						<button class="btn btn-warning">Run</button>
						<button class="btn btn-danger">Delete</button>
						<button class="btn btn-info">Help</button>
					</div>
					<div class="well">
						<textarea rows="20" cols="75"
							placeholder="enter business requirement here"></textarea>
					</div>
				</div>
			</div>
			<div class="col-sm-2 sidenav">
				<div class="well">
					<p>ADS</p>
				</div>
				<div class="well"></div>
			</div>
		</div>
	</div>
	<!-- Project Modal START -->
	<div id="modal" class="modal fade" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 ">
							<h3 class="m-t-none m-b ">Create New Project</h3>
							<form id="projectForm" action="projectCon" method="post">
								<div class="form-group">
									<label>Project Name</label> <input type="text"
										name="project_name" class="form-control">
								</div>
								<div class="form-group">
									<input type="submit" value="Create"
										class="btn btn-success pull-right">
								</div>


								<button class="btn btn-primary m-t-n-xs" data-dismiss="modal">Close</button>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div><!--Project modal END  -->
	<!-- TEst suite Modal START -->
	<div id="testsuite-modal" class="modal fade" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 ">
							<h3 class="m-t-none m-b ">Create New Test Suite</h3>
							<form action="projectCon" method="post">
								<div class="form-group">
									<label>Description</label> <input type="text"
										name="project_name" class="form-control">
								</div>
								<div class="form-group">
									<input type="submit" value="Create"
										class="btn btn-success pull-right">
								</div>


								<button class="btn btn-primary m-t-n-xs" data-dismiss="modal">Close</button>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div><!--test suite modal END  -->
	<footer class="container-fluid text-center">
		<p>Footer Text</p>
	</footer>

</body>
<script>

/* load projects from db */
 $(document).ready(function(){
	 $.ajax({
		 type:'get',
		 url:'projectCon?action=project_list',
		dataType:'json',
		success: function(data){
			$.each(data,function(i,obj){
				var html='<li><span class="hasmenu">'+obj.projectname+'</span></li>'
		         $("#project_list").append(html);
			});
		
		}	 
	 }); 
 });


$(function () {
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
});
//right button click
/* 
$(document).ready(function(){ 
  //document.oncontextmenu = function() {return false;};

  $(document).mousedown(function(e){ 
    if( e.button == 2 ) { 
      alert('Right mouse button!'); 
      return false; 
    } 
    return true; 
  }); 
}); */
//right click menu

$(document).contextmenu({
    delegate: ".hasmenu",
    autoFocus: true,
    preventContextMenuForPopup: true,
    preventSelect: true,
    taphold: true,
    menu: [{
        title: "Create Test Suite",
        cmd: "testsuite_create",
        uiIcon: "ui-icon-scissors"
    }, {
        title: "Copy <kbd>Ctrl+C</kbd>",
        cmd: "copy",
        uiIcon: "ui-icon-copy"
    }, {
        title: "Paste <kbd>Ctrl+V</kbd>",
        cmd: "paste",
        uiIcon: "ui-icon-clipboard",
        disabled: true
    }, {
        title: "----"
    }, {
        title: "More",
        children: [{
            title: "Sub 1 (callback)",
            action: function (event, ui) {
                alert("action callback sub1");
            }
        }, {
            title: "Edit <kbd>[F2]</kbd>",
            cmd: "sub2",
            tooltip: "Edit the title"
        }, ]
    }],
    // Handle menu selection to implement a fake-clipboard
    select: function (event, ui) {
        var $target = ui.target;
        switch (ui.cmd) {
            case "testsuite_create":
            	$('#testsuite-modal').modal('show');
                break
            case "paste":
                break
        }
       // alert("select " + ui.cmd + " on " + $target.text());
        // Optionally return false, to prevent closing the menu now
    },
    // Implement the beforeOpen callback to dynamically change the entries
    beforeOpen: function (event, ui) {
        var $menu = ui.menu,
            $target = ui.target,
            extraData = ui.extraData; // passed when menu was opened by call to open()

        // console.log("beforeOpen", event, ui, event.originalEvent.type);

        ui.menu.zIndex($(event.target).zIndex() + 1);

        $(document)
        //				.contextmenu("replaceMenu", [{title: "aaa"}, {title: "bbb"}])
        //				.contextmenu("replaceMenu", "#options2")
        //				.contextmenu("setEntry", "cut", {title: "Cuty", uiIcon: "ui-icon-heart", disabled: true})
        .contextmenu("setEntry", "copy", "Copy '" + $target.text() + "'")
            .contextmenu("setEntry", "paste", "Paste" + ("  :"))
            .contextmenu("enableEntry", "paste", (""));

        // Optionally return false, to prevent opening the menu now
    }
});

//project form submission
$("#projectForm").submit(function(e){
	e.preventDefault();
	
	$.ajax({
		type:'post',
		url:'projectCon',
		data:$(this).serialize(),
		success:function(data){
			if(data== 'true'){
				alert("Successfully created!");
				window.location="dashboard.jsp";
			}else{
				alert("Failed. data not saved!")
			}
		}
	});
});
</script>
</html>

