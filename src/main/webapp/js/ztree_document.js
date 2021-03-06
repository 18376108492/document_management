            var zTreeObj;
			//var setting={};
			// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
			var setting = {
                // async: {
                //     enable: true,//设置 zTree 是否开启异步加载模式
                //     url:"../asyncData/getNodes.php",
                //     autoParam:["id", "name=n", "level=lv"],
                //     otherParam:{"otherParam":"zTreeAsyncTest"},
                //     dataFilter: filter
                // },
				//是否允许编辑节点
				edit: {
                    drag: {
                        autoExpandTrigger: true,
                        prev: dropPrev,
                        inner: dropInner,
                        next: dropNext
                    },
					enable: true,
					editNameSelectAll: true,
					showRemoveBtn: true,
					removeTitle: "删除节点",
					showRenameBtn: true,
					renameTitle: "编辑节点",
                    showRemoveBtn: showRemoveBtn,
                    showRenameBtn: showRenameBtn
				},
				data: {
					key: {
						title: "title"
					},
					simpleData: {
						enable: true //使用简单数据类型
					}
				},
				callback: {
					onClick: zTreeOnClick, //注册节点的点击事件(单击事件)
                    onDblClick: OnDblClick,//双击事件
					beforeDrag: beforeDrag, //用于捕获节点被拖拽之前的事件回调函数，并且根据返回值确定是否允许开启拖拽操作
					beforeDrop: beforeDrop,
                    beforeDragOpen: beforeDragOpen,
                    onDrag: onDrag,
                    onDrop: onDrop,
                    onExpand: onExpand,

					beforeEditName: beforeEditName,
					beforeRemove: beforeRemove,
					beforeRename: beforeRename,
					onRemove: onRemove,
					onRename: onRename
				},
				view: {
					selectedMulti: false,
					addHoverDom: addHoverDom,
					//addHoverDom: addHoverDom, //移入节点显示编辑按钮
					removeHoverDom: removeHoverDom //移入节点显示编辑按钮
				},
				check: {
					enable: true
				}

			};

            function getQueryString(name) {
                var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
                var r = window.location.search.substr(1).match(reg);
                if (r != null) {
                    return decodeURI(r[2]);
                }
                return null;
            }
            var diskName = getQueryString("diskName");
            alert("diskName:"+diskName)



            // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
            // 这样调用：2
            //获取tree数据
            //初始化
            $(document).ready(function() {

                $.ajax({
                    type: 'GET',
                    contentType: '',
                    url: "/disk/init_disk?diskName="+diskName,
                    //timeout: 6000, //超时时间设置，单位毫秒
                    dataType: 'json',
                    success: function (data) {
                        var zNodes = data; //初始化ztree

                        alert("zNodes:"+zNodes)
                        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes); //初始化菜单树形结构
                    }
                });


				$("#hideNodesBtn").bind("click", {
					type: "rename"
				}, hideNodes);
				$("#showNodesBtn").bind("click", {
					type: "icon"
				}, showNodes);
				//setTitle();
				count();
			});




			//用于捕获节点被点击的事件回调函数（单击事件）
			//如果设置了 setting.callback.beforeClick 方法，且返回 false，将无法触发 onClick 事件回调函数
			function zTreeOnClick(event, treeId, treeNode) {
				alert(treeNode.tId + ", " + treeNode.name);
				count();
			};

            //双击事件：清除定时器，展开点击的树结构
            function OnDblClick(e, treeId, treeNode) {
            }



			function count() {
				function isForceHidden(node) {
					if(!node.parentTId) return false;
					var p = node.getParentNode();
					return !!p.isHidden ? true : isForceHidden(p);
				}
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
					checkCount = zTree.getCheckedNodes(true).length,
					nocheckCount = zTree.getCheckedNodes(false).length,
					hiddenNodes = zTree.getNodesByParam("isHidden", true),
					hiddenCount = hiddenNodes.length;

				for(var i = 0, j = hiddenNodes.length; i < j; i++) {
					var n = hiddenNodes[i];
					if(isForceHidden(n)) {
						hiddenCount -= 1;
					} else if(n.isParent) {
						hiddenCount += zTree.transformToArray(n.children).length;
					}
				}

				//复选框设置
				$("#isHiddenCount").text(hiddenNodes.length);
				$("#hiddenCount").text(hiddenCount);
				$("#checkCount").text(checkCount);
				$("#nocheckCount").text(nocheckCount);

				//拖拽节点控制
				setCheck();
				$("#copy").bind("change", setCheck);
				$("#move").bind("change", setCheck);
				$("#prev").bind("change", setCheck);
				$("#inner").bind("change", setCheck);
				$("#next").bind("change", setCheck);

				$("#selectAll").bind("click", selectAll);
				alert(checkCount);

			}


			function setTitle(node) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes = node ? [node] : zTree.transformToArray(zTree.getNodes());
				for(var i = 0, l = nodes.length; i < l; i++) {
					var n = nodes[i];
					n.title = "[" + n.id + "] isFirstNode = " + n.isFirstNode + ", isLastNode = " + n.isLastNode;
					zTree.updateNode(n);
				}
			}

			//展示节点
			function showNodes() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
					nodes = zTree.getNodesByParam("isHidden", true);
				zTree.showNodes(nodes);
				setTitle();
				count();
			}

			//隐藏节点
			function hideNodes() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
					nodes = zTree.getSelectedNodes();
				if(nodes.length == 0) {
					alert("请至少选择一个节点");
					return;
				}
				zTree.hideNodes(nodes);
				setTitle();
				count();
			}


			function setCheck() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
					isCopy = $("#copy").attr("checked"),
					isMove = $("#move").attr("checked"),
					prev = $("#prev").attr("checked"),
					inner = $("#inner").attr("checked"),
					next = $("#next").attr("checked");
				zTree.setting.edit.drag.isCopy = isCopy;
				zTree.setting.edit.drag.isMove = isMove;
				showCode(1, ['setting.edit.drag.isCopy = ' + isCopy, 'setting.edit.drag.isMove = ' + isMove]);

				zTree.setting.edit.drag.prev = prev;
				zTree.setting.edit.drag.inner = inner;
				zTree.setting.edit.drag.next = next;
				showCode(2, ['setting.edit.drag.prev = ' + prev, 'setting.edit.drag.inner = ' + inner, 'setting.edit.drag.next = ' + next]);

			}

			function showCode(id, str) {
				var code = $("#code" + id);
				code.empty();
				for(var i = 0, l = str.length; i < l; i++) {
					code.append("<li>" + str[i] + "</li>");
				}
			}

			//节点的编辑
			var log, className = "dark";


			function beforeEditName(treeId, treeNode) {
				className = (className === "dark" ? "" : "dark");
				showLog("[ " + getTime() + " beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.selectNode(treeNode);
				setTimeout(function() {
					if(confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
						setTimeout(function() {
							zTree.editName(treeNode);
						}, 0);
					}
				}, 0);
				return false;
			}
			//用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
			//命名前
			//1.treeId  String
			//对应 zTree 的 treeId，便于用户操控
			//2.treeNode  JSON
			//将要删除的节点 JSON 数据对象
			function beforeRemove(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				className = (className === "dark" ? "" : "dark");
				showLog("[ " + getTime() + " beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
				zTree.selectNode(treeNode);
				return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
			}

			//修改节点信息,用于捕获删除节点之后的事件回调函数。
			//1.eventjs  event 对象
			//标准的 js event 对象
			//2.treeId  String
			//对应 zTree 的 treeId， 便于用户操控
			//3.treeNode  JSON
			//将要删除的节点 JSON 数据对象
			//4.isCancel  Boolean
			//是否取消操作
			function onRemove(e, treeId, treeNode, isCancel) {
				showLog("[ " + getTime() + " onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
				if(isCancel) {
					return;
				}
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var onodes = zTree.getNodes()
				console.log(onodes);
				//发送请求修改节点信息
				var data = {
					"id": treeNode.id,
					"code": treeNode.pId, //父节点
					"name": treeNode.name,
				};
				$.ajax({
					type: 'post',
                    contentType:"application/json;charset=UTF-8",
					url: "/document/remove",
                    data: JSON.stringify(data),
					dataType: 'json',
					success: function(res) {
						layer.msg(res.msg)
					}
				});
			}

			//重命名之前的判断
			//1.treeId String
			//对应 zTree 的 treeId，便于用户操控
			//2.treeNode JSON
			//将要更改名称的节点 JSON 数据对象
			//3.newName String
			//修改后的新名称
			//4.isCancel Boolean
			//是否取消操作
			function beforeRename(treeId, treeNode, newName, isCancel) {
				className = (className === "dark" ? "" : "dark");
				showLog((isCancel ? "<span style='color:red'>" : "") + "[ " + getTime() + " beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>" : ""));
				if(newName.length == 0) {
					setTimeout(function() {
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						setTimeout(function() {
							zTree.editName(treeNode)
						}, 10);
						zTree.cancelEditName();
						alert("节点名称不能为空.");
					}, 0);
					return false;
				}
				return true;
			}

			//重命名节点信息
			//1.eventjs  event 对象
			//标准的 js event 对象
			//2.treeId  String
			//对应 zTree 的 treeId，便于用户操控
			//3.treeNode  JSON
			//被修改名称的节点 JSON 数据对象
			//4.isCancel  Boolean
			//是否取消操作 
			function onRename(e, treeId, treeNode, isCancel) {
				showLog((isCancel ? "<span style='color:red'>" : "") + "[ " + getTime() + " onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>" : ""));
                if(isCancel){
                	return;
                }
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var onodes = zTree.getNodes()
				console.log(onodes);
				//发送请求修改节点信息
				var data = {
					"id": treeNode.id,
					"code": treeNode.pId, //父节点
					"name": treeNode.name,
				};
				$.ajax({
					type: 'post',
                    contentType:"application/json;charset=UTF-8",
					url: "/document/update",
					data: JSON.stringify(data),
					timeout: 1000, //超时时间设置，单位毫秒
					dataType: 'json',
					success: function(res) {
						layer.msg(res.msg)
					}
				});
			}

			function showRemoveBtn(treeId, treeNode) {
				return !treeNode.isFirstNode;
			}

			function showRenameBtn(treeId, treeNode) {
				return !treeNode.isLastNode;
			}

			var newCount = 1;

			function addHoverDom(treeId, treeNode) {
				var sObj = $("#" + treeNode.tId + "_span");
				if(treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
				var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
					"' title='add node' onfocus='this.blur();'></span>";
				sObj.after(addStr);
				var btn = $("#addBtn_" + treeNode.tId);

				//当点击添加按钮时
				if(btn) btn.bind("click", function() {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					console.log(treeNode.id);
					var name = "新文件" + (newCount++);
					var newNode;
					//发送请求保存一个新建的节点，后台返回ID，用返回的ID新增节点
					// var data = {
					// 	"code": treeNode.id,
					// 	"name": name
					// };
					var list=[];
					list.push(treeNode.id);
					list.push(name);

					$.ajax({
						type: 'POST',
                        contentType:"application/json;charset=UTF-8",
						url: "/document/add",
						data:JSON.stringify(list),
						//data: data,
						timeout: 1000, //超时设置
						dataType: 'json',
						success: function(res) {
							console.log(res)
							if(res.status == 200) {
								var newId = res.data;
								newNode = zTree.addNodes(treeNode, {
									id: newId,
									pId: treeNode.id,
									name: name
								});
								zTree.editName(newNode[0]) //新增的节点进入编辑状态
							}
						}
					});
                   // zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
					return false;
				});
			};
			//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
			//请务必与 setting.view.removeHoverDom 同时使用；属于高级应用，使用时请确保对 zTree 比较了解。
			//默认值：null
			function removeHoverDom(treeId, treeNode) {
				$("#addBtn_" + treeNode.tId).unbind().remove();
				$("#diyBtn_space_" + treeNode.id).unbind().remove();

			};

			function selectAll() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
			}


            //拖拽到目标节点时，设置是否允许移动到目标节点前面的操作。
			// [setting.edit.enable = true 时生效]
            //拖拽目标是 根 的时候，不触发 prev 和 next，只会触发 inner
            //此功能主要作用是对拖拽进行适当限制（辅助箭头），
			// 需要结合 next、inner 一起使用，才能实现完整功能。
            function dropPrev(treeId, nodes, targetNode) {
                var pNode = targetNode.getParentNode();
                //alert("pNodePrev:"+pNode);
                if (pNode && pNode.dropInner === false) {
                    return false;
                } else {
                    for (var i=0,l=curDragNodes.length; i<l; i++) {
                        var curPNode = curDragNodes[i].getParentNode();
                        if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
                            return false;
                        }
                       // alert("curPNode:"+curPNode);
                    }
                }
                return true;
            }

            //拖拽到目标节点时，设置是否允许成为目标节点的子节点。
			// [setting.edit.enable = true 时生效]
            //拖拽目标是 根 的时候，不触发 prev 和 next，只会触发 inner
            //此功能主要作用是对拖拽进行适当限制（辅助箭头），
			// 需要结合 prev、next 一起使用，才能实现完整功能。
            function dropInner(treeId, nodes, targetNode) {
                if (targetNode && targetNode.dropInner === false) {
                    return false;
                } else {
                    for (var i=0,l=curDragNodes.length; i<l; i++) {
                        if (!targetNode && curDragNodes[i].dropRoot === false) {
                            return false;
                        } else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
                            return false;
                        }
                    }
                }
                return true;
            }

            //拖拽到目标节点时，设置是否允许成为目标节点的子节点。
			// [setting.edit.enable = true 时生效]
            //拖拽目标是 根 的时候，不触发 prev 和 next，只会触发 inner
            //此功能主要作用是对拖拽进行适当限制（辅助箭头），
			// 需要结合 prev、next 一起使用，才能实现完整功能。
            function dropNext(treeId, nodes, targetNode) {
                var pNode = targetNode.getParentNode();
               // alert("pNodeNext:"+pNode);
                if (pNode && pNode.dropInner === false) {
                    return false;
                } else {
                    for (var i=0,l=curDragNodes.length; i<l; i++) {
                        var curPNode = curDragNodes[i].getParentNode();
                        //alert("curPNode:"+curPNode);
                        if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
                            return false;
                        }
                    }
                }

                return true;
            }

            var log, className = "dark", curDragNodes, autoExpandNode;
            function beforeDrag(treeId, treeNodes) {
                className = (className === "dark" ? "":"dark");
                showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
                for (var i=0,l=treeNodes.length; i<l; i++) {
                    if (treeNodes[i].drag === false) {
                        curDragNodes = null;
                        return false;
                    } else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
                        curDragNodes = null;
                        return false;
                    }
                }
                curDragNodes = treeNodes;
                return true;
            }
            function beforeDragOpen(treeId, treeNode) {
                autoExpandNode = treeNode;
                return true;
            }
            function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
                className = (className === "dark" ? "":"dark");
                showLog("[ "+getTime()+" beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
                showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"));
                return true;
            }
            function onDrag(event, treeId, treeNodes) {
                className = (className === "dark" ? "":"dark");
                showLog("[ "+getTime()+" onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
            }
            function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
                className = (className === "dark" ? "":"dark");
                showLog("[ "+getTime()+" onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
                showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"))

                var list=[];
                list.push(treeNodes[0].id);//移动的文件ID
                list.push(treeNodes[0].pId);//移动的父类文件ID
                list.push(targetNode.id);//移动后的夫文件ID

                $.ajax({
                    type: 'post',
                    contentType:"application/json;charset=UTF-8",
                    url: "/document/drop",
                    data: JSON.stringify(list),
                    timeout: 6000, //超时时间设置，单位毫秒
                    dataType: 'json',
                    success: function(res) {
                        layer.msg(res.msg)
                    }
                });

            }
            function onExpand(event, treeId, treeNode) {
                if (treeNode === autoExpandNode) {
                    className = (className === "dark" ? "":"dark");
                    showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
                }
            }

            function showLog(str) {
                if (!log) log = $("#log");
                log.append("<li class='"+className+"'>"+str+"</li>");
                if(log.children("li").length > 8) {
                    log.get(0).removeChild(log.children("li")[0]);
                }
            }
            function getTime() {
                var now= new Date(),
                    h=now.getHours(),
                    m=now.getMinutes(),
                    s=now.getSeconds(),
                    ms=now.getMilliseconds();
                return (h+":"+m+":"+s+ " " +ms);
            }

            function setTrigger() {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr("checked");
            }


// ===========================


