(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-181317d0"],{"20fa":function(e,t,n){"use strict";n.r(t);var o=n("7a23"),r={style:{width:"100%"}},c=Object(o["createElementVNode"])("h3",null,"部門列表",-1),a=Object(o["createTextVNode"])("修改"),l=Object(o["createTextVNode"])("輸入"),i=Object(o["createTextVNode"])("刪除"),d=Object(o["createTextVNode"])("輸入"),u=Object(o["createTextVNode"])("刪除"),s={class:"dialog-footer"},b=Object(o["createTextVNode"])("取消");function p(e,t,n,p,f,m){var j=Object(o["resolveComponent"])("search"),O=Object(o["resolveComponent"])("el-icon"),h=Object(o["resolveComponent"])("el-button"),V=Object(o["resolveComponent"])("el-input"),C=Object(o["resolveComponent"])("el-col"),g=Object(o["resolveComponent"])("el-row"),N=Object(o["resolveComponent"])("el-table-column"),w=Object(o["resolveComponent"])("el-table"),x=Object(o["resolveComponent"])("el-pagination"),_=Object(o["resolveComponent"])("el-card"),v=Object(o["resolveComponent"])("el-form-item"),P=Object(o["resolveComponent"])("el-form"),D=Object(o["resolveComponent"])("el-option"),k=Object(o["resolveComponent"])("el-select"),y=Object(o["resolveComponent"])("el-dialog");return Object(o["openBlock"])(),Object(o["createElementBlock"])("div",r,[c,Object(o["createVNode"])(_,{class:"box-card"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(g,{gutter:24},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(C,{span:8},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{placeholder:"部門代號",modelValue:f.queryInfo.searchName,"onUpdate:modelValue":t[0]||(t[0]=function(e){return f.queryInfo.searchName=e}),clearable:"",onClear:m.getDepnoList,onKeyup:Object(o["withKeys"])(m.getDepnoList,["enter"])},{append:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(h,{class:"search_button",onClick:m.getDepnoList},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(O,null,{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(j)]})),_:1})]})),_:1},8,["onClick"])]})),_:1},8,["modelValue","onClear","onKeyup"])]})),_:1})]})),_:1}),Object(o["createVNode"])(w,{data:f.depnoList,style:{width:"100%"}},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(N,{type:"index",label:"編號",width:"40"}),Object(o["createVNode"])(N,{prop:"depname",label:"部門代號"}),Object(o["createVNode"])(N,{prop:"depnamech",label:"部門名稱"}),Object(o["createVNode"])(N,{label:"操作"},{default:Object(o["withCtx"])((function(e){return[Object(o["createVNode"])(h,{class:"edit_button",onClick:function(t){return m.showEditDialon(e.row.id)}},{default:Object(o["withCtx"])((function(){return[a]})),_:2},1032,["onClick"])]})),_:1})]})),_:1},8,["data"]),Object(o["createVNode"])(x,{"current-page":f.queryInfo.pageno,"page-size":f.queryInfo.pagesize,layout:"total, prev, pager, next, jumper",total:f.total,onCurrentChange:m.handleCurrentChange},null,8,["current-page","page-size","total","onCurrentChange"])]})),_:1}),Object(o["createVNode"])(y,{modelValue:f.editDialogVisible,"onUpdate:modelValue":t[12]||(t[12]=function(e){return f.editDialogVisible=e}),onClose:m.editDialogClosed,width:"60%",title:"部門資料編輯"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(P,{ref:"addFormRef",model:f.addForm,"label-width":"120px",rules:f.addFormRules},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(v,{label:"部門代號",prop:"depname"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{modelValue:f.addForm.depname,"onUpdate:modelValue":t[1]||(t[1]=function(e){return f.addForm.depname=e})},null,8,["modelValue"])]})),_:1}),Object(o["createVNode"])(v,{label:"部門名稱",prop:"setnamech"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{modelValue:f.addForm.depnamech,"onUpdate:modelValue":t[2]||(t[2]=function(e){return f.addForm.depnamech=e})},null,8,["modelValue"])]})),_:1})]})),_:1},8,["model","rules"]),Object(o["createVNode"])(_,{class:"box-card",style:{width:"80%"}},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(P,{"label-width":"120px",model:f.csrPotDepno,rules:f.addFormRules},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(v,{label:"消毒鍋代號",prop:"disinfection"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(k,{modelValue:f.csrPotDepno.disinfection,"onUpdate:modelValue":t[3]||(t[3]=function(e){return f.csrPotDepno.disinfection=e}),placeholder:"請選擇",clearable:""},{default:Object(o["withCtx"])((function(){return[(Object(o["openBlock"])(!0),Object(o["createElementBlock"])(o["Fragment"],null,Object(o["renderList"])(e.disinfectionList,(function(e){return Object(o["openBlock"])(),Object(o["createBlock"])(D,{key:e.disinfection,label:e.potname,value:e.disinfection},null,8,["label","value"])})),128))]})),_:1},8,["modelValue"])]})),_:1}),Object(o["createVNode"])(v,{label:"字首",prop:"top"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{modelValue:f.csrPotDepno.head,"onUpdate:modelValue":t[4]||(t[4]=function(e){return f.csrPotDepno.head=e})},null,8,["modelValue"])]})),_:1}),Object(o["createVNode"])(v,{label:"起始編號",prop:"x"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(k,{modelValue:f.csrPotDepno.beginnum,"onUpdate:modelValue":t[5]||(t[5]=function(e){return f.csrPotDepno.beginnum=e}),placeholder:"請選擇",clearable:""},{default:Object(o["withCtx"])((function(){return[(Object(o["openBlock"])(!0),Object(o["createElementBlock"])(o["Fragment"],null,Object(o["renderList"])(f.num,(function(e){return Object(o["openBlock"])(),Object(o["createBlock"])(D,{key:e,label:e,value:e},null,8,["label","value"])})),128))]})),_:1},8,["modelValue"])]})),_:1}),Object(o["createVNode"])(v,{label:"鍋別數量",prop:"x"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(k,{modelValue:f.csrPotDepno.potnum,"onUpdate:modelValue":t[6]||(t[6]=function(e){return f.csrPotDepno.potnum=e}),placeholder:"請選擇",clearable:""},{default:Object(o["withCtx"])((function(){return[(Object(o["openBlock"])(!0),Object(o["createElementBlock"])(o["Fragment"],null,Object(o["renderList"])(f.num,(function(e){return Object(o["openBlock"])(),Object(o["createBlock"])(D,{key:e,label:e,value:e},null,8,["label","value"])})),128))]})),_:1},8,["modelValue"])]})),_:1}),Object(o["createVNode"])(h,{class:"edit_button",onClick:m.addPot},{default:Object(o["withCtx"])((function(){return[l]})),_:1},8,["onClick"])]})),_:1},8,["model","rules"]),Object(o["createVNode"])(w,{data:f.addForm.csrPotDepno,style:{width:"100%"},"empty-text":"此欄位無資料"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(N,{prop:"disinfection",label:"消毒鍋代號",width:"180"}),Object(o["createVNode"])(N,{prop:"head",label:"字首",width:"180"}),Object(o["createVNode"])(N,{prop:"beginnum",label:"起始編號"}),Object(o["createVNode"])(N,{prop:"potnum",label:"鍋別數量"}),Object(o["createVNode"])(N,{label:"操作"},{default:Object(o["withCtx"])((function(e){return[Object(o["createVNode"])(h,{class:"delete_button",onClick:function(t){return m.deletePot(e.row.id,e.row.disinfection)}},{default:Object(o["withCtx"])((function(){return[i]})),_:2},1032,["onClick"])]})),_:1})]})),_:1},8,["data"])]})),_:1}),Object(o["createVNode"])(_,{class:"box-card",style:{width:"80%"}},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(P,{ref:"addFormRef2","label-width":"120px",model:f.csrPrinter,rules:f.addFormRules},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(v,{label:"條碼機名稱",prop:"printername"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{modelValue:f.csrPrinter.printername,"onUpdate:modelValue":t[7]||(t[7]=function(e){return f.csrPrinter.printername=e})},null,8,["modelValue"])]})),_:1}),Object(o["createVNode"])(v,{label:"條碼機安裝檔名稱",prop:"type"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{modelValue:f.csrPrinter.type,"onUpdate:modelValue":t[8]||(t[8]=function(e){return f.csrPrinter.type=e})},null,8,["modelValue"])]})),_:1}),Object(o["createVNode"])(v,{label:"IP位址設定",prop:"address"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{modelValue:f.csrPrinter.address,"onUpdate:modelValue":t[9]||(t[9]=function(e){return f.csrPrinter.address=e})},null,8,["modelValue"])]})),_:1}),Object(o["createVNode"])(v,{label:"埠號",prop:"port"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(V,{modelValue:f.csrPrinter.port,"onUpdate:modelValue":t[10]||(t[10]=function(e){return f.csrPrinter.port=e})},null,8,["modelValue"])]})),_:1}),Object(o["createVNode"])(h,{class:"edit_button",onClick:m.addPrinter},{default:Object(o["withCtx"])((function(){return[d]})),_:1},8,["onClick"])]})),_:1},8,["model","rules"]),Object(o["createVNode"])(w,{data:f.addForm.csrPrinter,style:{width:"100%"},"empty-text":"此欄位無資料"},{default:Object(o["withCtx"])((function(){return[Object(o["createVNode"])(N,{prop:"printername",label:"條碼機名稱",width:"180"}),Object(o["createVNode"])(N,{prop:"type",label:"條碼機安裝檔名稱"}),Object(o["createVNode"])(N,{prop:"address",label:"IP位址設定"}),Object(o["createVNode"])(N,{prop:"port",label:"埠號"}),Object(o["createVNode"])(N,{label:"操作"},{default:Object(o["withCtx"])((function(e){return[Object(o["createVNode"])(h,{class:"delete_button",onClick:function(t){return m.deletePrinter(e.row.id,e.row.printername)}},{default:Object(o["withCtx"])((function(){return[u]})),_:2},1032,["onClick"])]})),_:1})]})),_:1},8,["data"])]})),_:1})]})),footer:Object(o["withCtx"])((function(){return[Object(o["createElementVNode"])("div",s,[Object(o["createVNode"])(h,{type:"info",onClick:t[11]||(t[11]=function(e){return f.editDialogVisible=!1})},{default:Object(o["withCtx"])((function(){return[b]})),_:1})])]})),_:1},8,["modelValue","onClose"])])}var f={data:function(){return{queryInfo:{searchName:"",pageno:"",pagesize:""},csrPotDepno:{},csrPrinter:{},depnoList:[],num:[],addForm:{},total:"",addDialogVisible:!1,editDialogVisible:!1,addFormRules:{depname:[{required:!0,message:"請輸入盤包代號...",trigger:"blur"}],printername:[{required:!0,message:"請輸入條碼機名稱...",trigger:"blur"}],address:[{required:!0,message:"請輸入IP...",trigger:"blur"},{pattern:/^((25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)\.){3}(25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)*$/,message:"格式不正確",trigger:"blur"}],port:[{required:!0,message:"請輸入port...",trigger:"blur"},{type:"integer",message:"請輸入整數",trigger:"blur"}]}}},created:function(){this.getDepnoList(),this.getDisinfection(),this.addNum()},methods:{getDepnoList:function(){var e=this;this.$axios.get("/depno",this.queryInfo).then((function(t){e.total=t.data.data.total,e.depnoList=t.data.data.list}))},getDisinfection:function(){var e=this;this.$axios.get("/disinfection").then((function(t){e.disinfectionList=t.data.data.list}))},handleCurrentChange:function(e){this.queryInfo.pageno=e,this.getDepnoList()},editDialogClosed:function(){this.$refs.addFormRef.resetFields(),this.$refs.addFormRef2.resetFields(),this.addForm={},this.csrPrinter={},this.csrPotDepno={}},addPot:function(){var e=this;this.$refs.addFormRef.validate((function(t){t&&(e.csrPotDepno.depno=e.addForm.depno,e.$axios.post("/depno/potdata",e.csrPotDepno).then((function(){e.editDialogVisible=!1})))}))},addPrinter:function(){var e=this;this.$refs.addFormRef.validate((function(t){t&&(e.csrPrinter.depno=e.addForm.depno,e.$axios.post("/depno/printer",e.csrPrinter).then((function(){e.editDialogVisible=!1})))}))},showEditDialon:function(e){var t=this;this.$axios.get("/depno/"+e).then((function(e){t.addForm=e.data.data})),this.editDialogVisible=!0},deletePot:function(e,t){var n=this;this.$msgbox.confirm("確定要刪除 "+t+" ?","刪除",{cancelButtonText:"取消",confirmButtonText:"確定",type:"warning"}).then((function(){n.$axios.remove("/depno/potdata/"+e).then((function(){n.editDialogVisible=!1}))})).catch((function(){}))},deletePrinter:function(e,t){var n=this;this.$msgbox.confirm("確定要刪除 "+t+" ?","刪除",{cancelButtonText:"取消",confirmButtonText:"確定",type:"warning"}).then((function(){n.$axios.remove("/depno/printer/"+e).then((function(){n.editDialogVisible=!1}))})).catch((function(){}))},addNum:function(){for(var e=1;e<=20;e++)this.num.push(e)}}},m=(n("32da"),n("6b0d")),j=n.n(m);const O=j()(f,[["render",p]]);t["default"]=O},"32da":function(e,t,n){"use strict";n("e55a")},e55a:function(e,t,n){}}]);
//# sourceMappingURL=chunk-181317d0.28ae39c2.js.map