(this.webpackJsonpfrontend=this.webpackJsonpfrontend||[]).push([[0],{101:function(e,a,t){e.exports={title:"HeaderDrawer_title__fxE8i",icon:"HeaderDrawer_icon__2hb9h",search:"HeaderDrawer_search__2bhyO"}},118:function(e,a,t){e.exports={card:"PropositionsCard_card__3G0oM",type:"PropositionsCard_type__1XTqh",pdf:"PropositionsCard_pdf__2x4IH",details:"PropositionsCard_details__3xLQF"}},119:function(e,a,t){e.exports={container:"Profile_container__1GgxV",name:"Profile_name__1EiIH",party:"Profile_party__24r5j",image:"Profile_image__13oD_"}},152:function(e,a,t){e.exports={container:"ExpensesDetailsSection_container__eVAq1",click:"ExpensesDetailsSection_click__GK6Of",counterContainer:"ExpensesDetailsSection_counterContainer__soCDJ"}},153:function(e,a,t){e.exports={container:"Expenses_container__2UHaj",chart:"Expenses_chart__22ebR",left:"Expenses_left__7i8bh"}},154:function(e,a,t){e.exports={card:"NewsCard_card__iPdxD",title:"NewsCard_title__1J1AV",description:"NewsCard_description__3o8As"}},155:function(e,a,t){e.exports={logo:"LandingForm_logo__12hoo",container:"LandingForm_container__324PD",title:"LandingForm_title__RHJUc"}},157:function(e,a,t){e.exports={container:"HeaderLogo_container__JFVSX",logo:"HeaderLogo_logo__3VhH1",title:"HeaderLogo_title__3Nbpe"}},190:function(e,a,t){e.exports={container:"ExpensesRadio_container__1puw3",radio:"ExpensesRadio_radio__3QIxD"}},193:function(e,a,t){e.exports={timeline:"PropositionTree_timeline__1qtUj",switcher:"PropositionTree_switcher__-4yrO"}},232:function(e,a,t){e.exports={footerContainer:"JarbasReimbursementTag_footerContainer__1I91d"}},251:function(e,a,t){e.exports={container:"EmptyData_container__3QVyQ"}},256:function(e,a,t){e.exports={card:"ProfileCard_card__1Z2DV"}},257:function(e,a,t){e.exports={container:"Search_container__N-GVx"}},259:function(e,a,t){e.exports={container:"News_container__29Q-m"}},260:function(e,a,t){e.exports={container:"Landing_container__1YSU_"}},261:function(e,a,t){e.exports={video:"LandingVideo_video__20pkI"}},262:function(e,a,t){e.exports=t.p+"static/media/protests_240.2b216380.mp4"},263:function(e,a,t){e.exports=t.p+"static/media/protests_144.c98cd51d.mp4"},269:function(e,a,t){e.exports={container:"Propositions_container__114mr"}},270:function(e,a,t){e.exports={container:"Header_container__38xwa"}},274:function(e,a,t){e.exports=t.p+"static/media/github-icon.213b75c7.png"},275:function(e,a,t){e.exports={main:"MainContent_main__3Tn1n"}},280:function(e,a,t){e.exports=t(500)},285:function(e,a,t){},40:function(e,a,t){e.exports={container:"FeaturesShowcase_container__2SIa8",card:"FeaturesShowcase_card__gFwuJ",icon:"FeaturesShowcase_icon__1E68S",title:"FeaturesShowcase_title__2_naB",description:"FeaturesShowcase_description__7Je_B"}},499:function(e,a,t){},500:function(e,a,t){"use strict";t.r(a);var n=t(0),r=t.n(n),i=t(13),c=t.n(i),o=(t(285),t(44)),s="EXPENSES_ACTIONS.REQUEST_EXPENSES";var l="EXPENSES_ACTIONS.RECEIVE_EXPENSES";var u="EXPENSES_ACTIONS.FAILED_EXPENSES";function m(e){return{type:u,error:e}}var p="EXPENSES_ACTIONS.SHOW_DETAILS";var d="EXPENSES_ACTIONS.RADIO_CHANGED";var E={url:""};function f(e,a){return function(t){t({type:s});var n="".concat(E.url,"/politicians/").concat(e,"/monthlyexpenses"),r=new URLSearchParams(a).toString();fetch("".concat(n,"?").concat(r)).then((function(e){if(e.ok)return e.json();t(m(e))})).then((function(e){return t({type:l,response:e})})).catch((function(e){t(m(e))}))}}var h=t(229);var _=function(e){return r.a.createElement(h.a,{data:{labels:e.data.dates,datasets:[{fill:!1,label:"Despesas",backgroundColor:"#0078ff",borderColor:"#0078ff",data:e.data.values}]},options:{responsive:!0,maintainAspectRatio:!1,onClick:e.onDataClick,scales:{yAxes:[{ticks:{callback:function(e){return"R$ "+e}}}]}}})},v=t(152),g=t.n(v),b=t(187),S=t.n(b),N=t(231),C=t(71),y=t(506),O=t(79),x=t(72),w=t.n(x),I=t(520),R=t(36),j=t(47),P=t(62),D=t(508),k=t(517),A=t(507),T=t(194),L=t(248),F=t(232),H=t.n(F);var U=function(e){var a,t=D.a.Text,n=new URLSearchParams(window.location.search).get("politician"),i=function(e){e?window.open("https://jarbas.serenata.ai/dashboard/chamber_of_deputies/reimbursement/?q=".concat(e,"&is_suspicions=yes"),"_blank"):window.open("https://jarbas.serenata.ai/dashboard/chamber_of_deputies/reimbursement/?is_suspicions=yes","_blank")},c=function(){fetch("".concat(E.url,"/politicians/").concat(n)).then((function(e){if(e.ok)return e.json();i()})).then((function(e){return i(e.name)})).catch((function(e){return i()}))};return r.a.createElement(L.a,{spinning:e.isLoading},r.a.createElement("div",null,e.reimbursement?(a=e.reimbursement.suspicions)?r.a.createElement(k.a,{title:"Suspeitas",trigger:"click",content:function(){var e=[];for(var n in a)a[n].value&&e.push(a[n]);return r.a.createElement(A.b,{size:"small",dataSource:e,footer:r.a.createElement("div",{className:H.a.footerContainer},r.a.createElement(t,{type:"secondary"},"Powered by Jarbas"),r.a.createElement(T.a,{type:"primary",size:"small",onClick:c,danger:!0},"Detalhes")),renderItem:function(e){return r.a.createElement(A.b.Item,null,e.description)}})}},r.a.createElement(T.a,{type:"primary",danger:!0},"Suspeito")):null:null))};var J=function(e){var a=Object(n.useState)({}),t=Object(C.a)(a,2),i=t[0],c=t[1],o=Object(n.useState)(!1),s=Object(C.a)(o,2),l=s[0],u=s[1],m=e.data.documentCode;return Object(n.useEffect)((function(){if(m&&m>0){var a="".concat(E.url,"/expenses/").concat(m);function t(){return(t=Object(N.a)(S.a.mark((function e(){return S.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,new Promise((function(e){return setTimeout(e,6e4)}));case 2:u(!1);case 3:case"end":return e.stop()}}),e)})))).apply(this,arguments)}u(!0),fetch(a).then((function(e){if(e.ok)return e.json();u(!1),c(null)})).then((function(a){u(!1),c(a),a.suspicions&&e.onFindSuspicion(a.suspicions)})).catch((function(e){u(!1),c(null)})),function(){t.apply(this,arguments)}()}}),[m]),r.a.createElement(y.a,{title:r.a.createElement(O.a,{title:e.data.provider},e.data.provider),className:w.a.card,actions:[e.data.documentUrl?r.a.createElement(O.a,{title:"Comprovante"},r.a.createElement("a",{href:e.data.documentUrl,target:"_blank",rel:"noopener noreferrer"},r.a.createElement(R.a,{icon:P.b,key:"document",size:"lg"}))):r.a.createElement(O.a,{title:"Sem comprovante"},r.a.createElement(I.a,null))]},r.a.createElement("p",null,e.data.type),r.a.createElement("div",{className:w.a.detailsContainer},r.a.createElement("div",null,r.a.createElement("div",{className:w.a.money},r.a.createElement(R.a,{icon:j.b,key:"value",size:"lg",className:w.a.moneyIcon}),"R$ "+e.data.value.toLocaleString()),r.a.createElement("div",{className:w.a.date},r.a.createElement(R.a,{icon:P.a,key:"date",size:"lg",className:w.a.icon}),e.data.yearMonth.split("-")[1]+"/"+e.data.yearMonth.split("-")[0])),r.a.createElement("div",{className:w.a.jarbasReimbursementContainer},r.a.createElement(U,{isLoading:l,reimbursement:i}))))},V=t(518);var q=function(e){return r.a.createElement("div",null,e.count>0?r.a.createElement(V.a,{color:"#ff4d4f"},"Despesas suspeitas: ",e.count):null)};var z=function(e){var a=[];return e.data.forEach((function(t){a.push(r.a.createElement(J,{onFindSuspicion:e.incrementSuspicions,data:t,key:t.documentNumber+"-"+t.documentCode}))})),r.a.createElement("div",{className:g.a.container},r.a.createElement("div",{className:g.a.counterContainer},r.a.createElement(q,{count:e.suspicionsCount})),e.data.length>0?a:r.a.createElement("p",{className:g.a.click},"Clique em uma despesa no gr\xe1fico para visualizar os detalhes."))},B=t(153),X=t.n(B),M=t(504),Q=t(251),G=t.n(Q),W=t(102);var K=function(){return r.a.createElement("div",{className:G.a.container},r.a.createElement(W.a,{description:"Sem dados"}))},Y=t(503),Z=t(190),$=t.n(Z);var ee=function(e){return r.a.createElement("div",{className:$.a.container},r.a.createElement(Y.default.Group,{defaultValue:e.active,buttonStyle:"solid",onChange:function(a){return e.onChange(a.target.value)},className:$.a.radio},r.a.createElement(Y.default.Button,{value:1},"Esse ano"),r.a.createElement(Y.default.Button,{value:2},"\xdaltimos 6 meses"),r.a.createElement(Y.default.Button,{value:3},"Legislatura")))};var ae=function(e){var a=e.handleExpensesRequest,t=e.activeRadio,i=e.resetJarbasSuspicions,c=new URLSearchParams(window.location.search).get("politician");return Object(n.useEffect)((function(){switch(i(),t){case 2:a(c,{lastMonths:6});break;case 3:a(c);break;default:a(c,{years:(new Date).getFullYear()})}}),[a,c,t,i]),r.a.createElement(L.a,{tip:"Carregando...",spinning:e.loading},r.a.createElement(qa,{politicianId:c}),r.a.createElement(Ka,null,e.expenseData.monthlyExpenses&&e.expenseData.monthlyExpenses.length>0?r.a.createElement("div",{className:X.a.container},r.a.createElement(M.a,{direction:"vertical",className:X.a.left,size:"middle"},r.a.createElement(ee,{active:e.activeRadio,onChange:e.handleRadioChanged}),r.a.createElement("div",{className:X.a.chart},r.a.createElement(_,{data:e.expenseData,onDataClick:function(a,t){if(t.length>0)return e.resetJarbasSuspicions(),e.handleDetailsClick(t[0]._index)}}))),r.a.createElement(z,{data:e.detailsData,suspicionsCount:e.jarbasSuspicionsCount,incrementSuspicions:function(){return e.incrementJarbasSuspicions()}})):r.a.createElement(K,null)),r.a.createElement(Qa,null))},te=Object(o.b)((function(e){return e.expenses}),(function(e){return{handleExpensesRequest:function(a,t){return e(f(a,t))},handleDetailsClick:function(a){return e(function(e){return{type:p,index:e}}(a))},handleRadioChanged:function(a){return e(function(e){return{type:d,value:e}}(a))},incrementJarbasSuspicions:function(){return e({type:"EXPENSES_ACTIONS.INCREMENT_JARBAS_SUSPICIONS"})},resetJarbasSuspicions:function(){e({type:"EXPENSES_ACTIONS.RESET_JARBAS_SUSPICIONS"})}}}))(ae),ne=t(513),re=t(256),ie=t.n(re),ce=t(505),oe=t(46),se=t(521);var le=function(e){var a=y.a.Meta;return r.a.createElement(y.a,{hoverable:!0,className:ie.a.card,actions:[r.a.createElement(O.a,{title:"Despesas"},r.a.createElement(oe.a,{to:"/expenses?politician=".concat(e.profile.id)},r.a.createElement(R.a,{icon:j.a,size:"lg"}))),r.a.createElement(O.a,{title:"Not\xedcias"},r.a.createElement(oe.a,{to:"/news?politician=".concat(e.profile.id)},r.a.createElement(R.a,{icon:j.c,size:"lg"}))),r.a.createElement(O.a,{title:"Proposi\xe7\xf5es"},r.a.createElement(oe.a,{to:"/propositions?politician=".concat(e.profile.id)},r.a.createElement(R.a,{icon:P.b,size:"lg"})))]},r.a.createElement(a,{avatar:r.a.createElement(ce.a,{size:96,src:e.profile.picture,icon:r.a.createElement(se.a,null)}),title:r.a.createElement(O.a,{title:e.profile.name},e.profile.name),description:e.profile.party}))},ue=t(257),me=t.n(ue);var pe=function(e){var a=e.handleSearchRequest;return Object(n.useEffect)((function(){var e=new URLSearchParams(window.location.search).get("politicianName");e&&a(e)}),[a]),r.a.createElement(L.a,{tip:"Carregando...",spinning:e.loading},r.a.createElement(qa,null,r.a.createElement(Pa,{handleSearchSubmit:function(a){return e.handleSearchRequest(a)}})),r.a.createElement(Ka,null,r.a.createElement("div",{className:me.a.container},e.profiles.length>0?function(e){var a=[];return e.forEach((function(e){a.push(r.a.createElement(le,{profile:e,key:e.id}))})),a}(e.profiles):r.a.createElement(ne.a,{status:"404",title:"Nenhum pol\xedtico foi encontrado. Tente novamente com outro nome."}))),r.a.createElement(Qa,null))},de="SEARCH_ACTIONS.REQUEST_SEARCH";var Ee="SEARCH_ACTIONS.RECEIVE_SEARCH";var fe="SEARCH_ACTIONS.FAILED_SEARCH";function he(e){return{type:fe,error:e}}function _e(e){return function(a){a(function(e){return{type:de,politicianName:e}}(e)),fetch("".concat(E.url,"/profiles?name=").concat(e)).then((function(e){if(e.ok)return e.json();a(he(e))})).then((function(e){return a({type:Ee,response:e})})).catch((function(e){a(he(e))}))}}var ve=Object(o.b)((function(e){return e.search}),(function(e){return{handleSearchRequest:function(a){return e(_e(a))}}}))(pe),ge=t(154),be=t.n(ge);var Se=function(e){return r.a.createElement(y.a,{cover:r.a.createElement("img",{alt:"",src:e.data.imageLink}),className:be.a.card,hoverable:!0,onClick:function(){return window.open(e.data.link,"_blank")}},r.a.createElement("h4",{className:be.a.title},e.data.title),r.a.createElement("small",null,e.data.sourceName+" - "+new Date(e.data.publishedDate).toLocaleDateString()),r.a.createElement("p",{className:be.a.description},e.data.description))},Ne=t(259),Ce=t.n(Ne);var ye=function(e){var a=e.handleExpensesRequest,t=new URLSearchParams(window.location.search).get("politician");return Object(n.useEffect)((function(){a(t)}),[a,t]),r.a.createElement(L.a,{spinning:e.loading,tip:"Carregando..."},r.a.createElement(qa,{politicianId:t}),r.a.createElement(Ka,null,e.data.length>0?r.a.createElement("div",{className:Ce.a.container},function(e){var a=[];return e.forEach((function(e){a.push(r.a.createElement(Se,{data:e,key:e.link}))})),a}(e.data)):r.a.createElement(K,null)),r.a.createElement(Qa,null))},Oe="NEWS_ACTIONS.REQUEST_NEWS";var xe="NEWS_ACTIONS.RECEIVE_NEWS";var we="NEWS_ACTIONS.FAILED_NEWS";function Ie(e){return{type:we,error:e}}function Re(e){return function(a){a({type:Oe}),fetch("".concat(E.url,"/politicians/").concat(e,"/news")).then((function(e){if(e.ok)return e.json();a(Ie(e))})).then((function(e){return a({type:xe,response:e})})).catch((function(e){a(Ie(e))}))}}var je=Object(o.b)((function(e){return e.news}),(function(e){return{handleExpensesRequest:function(a){return e(Re(a))}}}))(ye),Pe=t(260),De=t.n(Pe),ke=t(92),Ae=t.n(ke),Te=t(155),Le=t.n(Te),Fe=t(39),He=Object(Fe.a)();var Ue=function(e){return r.a.createElement("div",{className:Le.a.container},r.a.createElement("img",{src:Ae.a,className:Le.a.logo,alt:"Not available"}),r.a.createElement("h1",{className:Le.a.title},"Congresso em chamas"),r.a.createElement(Pa,{handleSearchSubmit:function(e){return He.push("/search?politicianName=".concat(e))}}))},Je=t(261),Ve=t.n(Je),qe=t(262),ze=t.n(qe),Be=t(263),Xe=t.n(Be);var Me=function(){return r.a.createElement("video",{autoPlay:"autoplay",loop:"loop",muted:!0,className:Ve.a.video},r.a.createElement("source",{src:ze.a,type:"video/mp4"}),r.a.createElement("source",{src:Xe.a,type:"video/mp4"}),"This video is not available.")},Qe=t(40),Ge=t.n(Qe);var We=function(){var e="var(--theme-bg-color)";return r.a.createElement("div",{className:Ge.a.container},r.a.createElement("div",{className:Ge.a.card},r.a.createElement("div",{className:Ge.a.icon},r.a.createElement(R.a,{icon:j.a,size:"7x",color:e})),r.a.createElement("h3",{className:Ge.a.title},"Despesas"),r.a.createElement("p",{className:Ge.a.description},"Acompanhe todas as despesas lan\xe7adas na cota parlamentar dos deputados federais.")),r.a.createElement("div",{className:Ge.a.card},r.a.createElement("div",{className:Ge.a.icon},r.a.createElement(R.a,{icon:j.c,size:"7x",color:e})),r.a.createElement("h3",{className:Ge.a.title},"Not\xedcias"),r.a.createElement("p",{className:Ge.a.description},"Os \xfaltimos artigos e not\xedcias dos deputados federais com a curadoria do Google News API.")),r.a.createElement("div",{className:Ge.a.card},r.a.createElement("div",{className:Ge.a.icon},r.a.createElement(R.a,{icon:P.b,size:"7x",color:e})),r.a.createElement("h3",{className:Ge.a.title},"Proposi\xe7\xf5es"),r.a.createElement("p",{className:Ge.a.description},"Projetos de lei, resolu\xe7\xf5es, medidas provis\xf3rias e mais. Acompanhe a atividade dos deputados federais na c\xe2mara.")))};var Ke=function(e){return r.a.createElement("div",null,r.a.createElement("div",{className:De.a.container},r.a.createElement(Ue,null),r.a.createElement(Me,null)),r.a.createElement(We,null),r.a.createElement(Qa,null))},Ye=t(118),Ze=t.n(Ye),$e=t(523),ea=t(519),aa=t(515),ta=t(511),na=t(193),ra=t.n(na),ia=t(522);var ca=function(e){var a=Object(n.useState)([]),t=Object(C.a)(a,2),i=t[0],c=t[1],o=Object(n.useState)([]),s=Object(C.a)(o,2),l=s[0],u=s[1],m=function(e){e.processingHistory.sort((function(e,a){return a.sequence-e.sequence}));var a=e.processingHistory.map((function(e){return r.a.createElement(aa.a.Item,{color:"var(--theme-bg-color)",label:new Date(e.timestamp).toLocaleDateString(),key:e.id},r.a.createElement("p",null,e.description))}));return r.a.createElement(aa.a,{mode:"right",className:ra.a.timeline},a)},p=function(e){return e<10?[13,0]:e<100?[17,0]:[20,0]};return r.a.createElement(ta.a,{treeData:function(e){var a=[{title:r.a.createElement(ea.a,{style:{backgroundColor:"var(--theme-bg-color)"},count:e.authors.length,offset:p(e.authors.length)},"Autores"),key:"0-0",children:[]},{title:r.a.createElement(ea.a,{style:{backgroundColor:"var(--theme-bg-color)"},count:e.processingHistory.length,offset:p(e.processingHistory.length)},"Hist\xf3rico de tramita\xe7\xe3o"),key:"0-1",children:[{title:m(e),key:"0-1-0"}]}];return e.authors.forEach((function(e,t){a[0].children.push({title:e,key:"0-0-"+t})})),a}(e.proposition),onSelect:function(e,a){return function(e,a){var t=a.node;t.props.isLeaf?u(e):t.props.expanded?c(i.filter((function(e){return e!==t.props.eventKey}))):c(i.concat(t.props.eventKey))}(e,a)},onExpand:function(e){return c(e)},selectedKeys:l,expandedKeys:i,switcherIcon:r.a.createElement(ia.a,{className:ra.a.switcher})})};var oa=function(e){var a=e.proposition,t=a.title,n=a.link,i=a.typeDescription;return r.a.createElement(y.a,{className:Ze.a.card},r.a.createElement("h3",null,t),r.a.createElement("h4",{className:Ze.a.type},i),r.a.createElement("div",{className:Ze.a.details},r.a.createElement(ca,{proposition:e.proposition}),r.a.createElement("a",{href:n,target:"_blank",rel:"noopener noreferrer"},r.a.createElement($e.a,{className:Ze.a.pdf}))))},sa=t(269),la=t.n(sa);var ua=function(e){var a=e.handlePropositionsRequest,t=new URLSearchParams(window.location.search).get("politician");return Object(n.useEffect)((function(){a(t)}),[a,t]),r.a.createElement(L.a,{spinning:e.loading,tip:"Carregando..."},r.a.createElement(qa,{politicianId:t}),r.a.createElement(Ka,null,e.propositions.length>0?r.a.createElement("div",{className:la.a.container},e.propositions.map((function(e){return r.a.createElement(oa,{proposition:e,key:e.id})}))):r.a.createElement(K,null)),r.a.createElement(Qa,null))},ma="PROPOSITIONS_ACTIONS.REQUEST_PROPOSITIONS";var pa="PROPOSITIONS_ACTIONS.RECEIVE_PROPOSITIONS";var da="PROPOSITIONS_ACTIONS.FAILED_PROPOSITIONS";function Ea(e){return{type:da,error:e}}function fa(e){return function(a){a({type:ma}),fetch("".concat(E.url,"/politicians/").concat(e,"/propositions")).then((function(e){if(e.ok)return e.json();a(Ea(e))})).then((function(e){return a({type:pa,response:e})})).catch((function(e){a(Ea(e))}))}}var ha=Object(o.b)((function(e){return e.propositions}),(function(e){return{handlePropositionsRequest:function(a){return e(fa(a))}}}))(ua),_a=t(509),va=t(119),ga=t.n(va);function ba(e){return r.a.createElement("div",{className:ga.a.container},r.a.createElement(_a.a,{avatar:!0,paragraph:{rows:2,width:[100,55]},title:!1,loading:e.loading},r.a.createElement(ce.a,{size:54,src:e.profile.picture,className:ga.a.image,icon:r.a.createElement(se.a,null)}),r.a.createElement("div",null,r.a.createElement("h4",{className:ga.a.name},e.profile.name),r.a.createElement("h5",{className:ga.a.party},e.profile.party))))}var Sa=t(270),Na=t.n(Sa),Ca=t(516),ya=t(512),Oa=t(510),xa=t(100),wa=t(51),Ia=t(101),Ra=t.n(Ia),ja=t(514);var Pa=function(e){var a=ja.a.Search;return r.a.createElement(a,{placeholder:"Deputado federal",enterButton:!0,onSearch:function(a){return e.handleSearchSubmit(a)}})};var Da=function(e){var a=window.location.pathname,t=new URLSearchParams(window.location.search).get("politician");return r.a.createElement(ya.a,{visible:e.visible,onClose:e.onClose,placement:"left",title:r.a.createElement(oe.a,{to:"/"},r.a.createElement("div",{className:Ra.a.title},r.a.createElement("img",{src:Ae.a,height:70,alt:"No content available"})))},r.a.createElement(Oa.a,null,r.a.createElement("div",{className:Ra.a.search},r.a.createElement(Pa,{handleSearchSubmit:function(e){return He.push("/search?politicianName=".concat(e))}})),r.a.createElement(Oa.a.Item,{disabled:"/expenses"===a},r.a.createElement(oe.a,{to:"/expenses?politician=".concat(t)},r.a.createElement(xa.a,null,r.a.createElement(wa.a,{span:6,className:Ra.a.icon},r.a.createElement(R.a,{icon:j.a,size:"lg"})),r.a.createElement(wa.a,{span:18},"Despesas")))),r.a.createElement(Oa.a.Item,{disabled:"/news"===a},r.a.createElement(oe.a,{to:"/news?politician=".concat(t)},r.a.createElement(xa.a,null,r.a.createElement(wa.a,{span:6,className:Ra.a.icon},r.a.createElement(R.a,{icon:j.c,size:"lg"})),r.a.createElement(wa.a,{span:18},"Not\xedcias")))),r.a.createElement(Oa.a.Item,{disabled:"/propositions"===a},r.a.createElement(oe.a,{to:"/propositions?politician=".concat(t)},r.a.createElement(xa.a,null,r.a.createElement(wa.a,{span:6,className:Ra.a.icon},r.a.createElement(R.a,{icon:P.b,size:"lg"})),r.a.createElement(wa.a,{span:18},"Proposi\xe7\xf5es"))))))},ka=t(524),Aa=t(157),Ta=t.n(Aa);var La=function(e){return r.a.createElement("div",{className:Ta.a.container},r.a.createElement("img",{src:Ae.a,className:Ta.a.logo,alt:"Not available"}),r.a.createElement("h5",{className:Ta.a.title},"Congresso em chamas"))};var Fa="HEADER_ACTIONS.REQUEST_PROFILE";var Ha="HEADER_ACTIONS.RECEIVE_PROFILE";var Ua="HEADER_ACTIONS.FAILED_PROFILE";function Ja(e){return{type:Ua,error:e}}function Va(e){return function(a){a({type:Fa}),fetch("".concat(E.url,"/politicians/").concat(e)).then((function(e){if(e.ok)return e.json();a(Ja(e))})).then((function(e){return a({type:Ha,response:e})})).catch((function(e){a(Ja(e))}))}}var qa=Object(o.b)((function(e){return e.header}),(function(e){return{handleProfileLoading:function(a){return e(Va(a))}}}))((function(e){var a=Object(n.useState)(!1),t=Object(C.a)(a,2),i=t[0],c=t[1],o=e.handleProfileLoading,s=e.politicianId,l=e.loading;return Object(n.useEffect)((function(){s&&o(s)}),[o,s]),r.a.createElement("div",{className:Na.a.container},s?r.a.createElement(Ca.a,{offsetTop:25},r.a.createElement(T.a,{type:"primary",icon:r.a.createElement(ka.a,null),onClick:function(){return c(!i)}})):r.a.createElement(oe.a,{to:"/"},r.a.createElement(La,null)),r.a.createElement("div",null,e.children),s&&r.a.createElement(ba,{profile:e.profile,loading:s&&l}),r.a.createElement(Da,{visible:i,onClose:function(){return c(!1)}}))})),za=t(52),Ba=t.n(za),Xa=t(274),Ma=t.n(Xa);var Qa=function(){return r.a.createElement("footer",{className:Ba.a.container},r.a.createElement("div",{className:Ba.a.left},r.a.createElement("div",{className:Ba.a.title},"Sobre Congresso em Chamas"),r.a.createElement("p",{className:Ba.a.text},"Congresso em Chamas \xe9 uma aplica\xe7\xe3o web que entrega ferramentas para o acompanhamento do trabalho dos deputados federais atualmente em mandato. Visamos contribuir com a transpar\xeancia da atividade do legislativo."),r.a.createElement("div",{className:Ba.a.title},"Contato e suporte"),r.a.createElement("p",{className:Ba.a.text},"Envie um e-mail para: sitecongressoemchamas@gmail.com")),r.a.createElement("div",{className:Ba.a.right},r.a.createElement("div",{className:Ba.a.title},"Contribua!"),r.a.createElement("div",null,r.a.createElement("p",{className:Ba.a.text},"Esse \xe9 um projeto de c\xf3digo aberto, sem fins lucrativos, que depende de doa\xe7\xf5es para manter sua infraestrutura. Agradecemos contribui\xe7\xf5es financeiras ou de novas features."),r.a.createElement("div",{className:Ba.a.links},r.a.createElement("form",{action:"https://www.paypal.com/cgi-bin/webscr",method:"post",target:"_top"},r.a.createElement("input",{type:"hidden",name:"cmd",value:"_donations"}),r.a.createElement("input",{type:"hidden",name:"business",value:"DVUECCKXF39N6"}),r.a.createElement("input",{type:"hidden",name:"item_name",value:"Arrecadar fundos para manter a infraestrutura do site Congresso Em Chamas."}),r.a.createElement("input",{type:"hidden",name:"currency_code",value:"BRL"}),r.a.createElement("input",{type:"image",src:"https://www.paypalobjects.com/pt_BR/BR/i/btn/btn_donateCC_LG.gif",border:"0",name:"submit",title:"PayPal - The safer, easier way to pay online!",alt:"Fa\xe7a doa\xe7\xf5es com o bot\xe3o do PayPal"}),r.a.createElement("img",{alt:"Not available",border:"0",src:"https://www.paypal.com/pt_BR/i/scr/pixel.gif",width:"1",height:"1"})),r.a.createElement("a",{href:"https://github.com/gpr-indevelopment/congresso_em_chamas",target:"_blank",rel:"noopener noreferrer"},r.a.createElement("img",{src:Ma.a,height:30,width:30,alt:"Not available"}))))))},Ga=t(275),Wa=t.n(Ga);var Ka=function(e){return r.a.createElement("main",{className:Wa.a.main},e.children)},Ya=t(28);t(499);var Za=function(){return r.a.createElement(Ya.b,{history:He},r.a.createElement(Ya.c,null,r.a.createElement(Ya.a,{exact:!0,path:"/"},r.a.createElement(Ke,null)),r.a.createElement(Ya.a,{path:"/search"},r.a.createElement(ve,null)),r.a.createElement(Ya.a,{exact:!0,path:"/news"},r.a.createElement(je,null)),r.a.createElement(Ya.a,{exact:!0,path:"/propositions"},r.a.createElement(ha,null)),r.a.createElement(Ya.a,{exact:!0,path:"/expenses"},r.a.createElement(te,null))))},$a=t(276),et={profiles:[],loading:!1};function at(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:et,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case de:return He.push("/search?politicianName=".concat(a.politicianName)),Object.assign({},e,{loading:!0});case Ee:return Object.assign({},e,{loading:!1,profiles:a.response});case fe:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var tt={loading:!1,rawData:[],expenseData:{},detailsData:[],activeRadio:1,jarbasSuspicionsCount:0};function nt(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:tt,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case s:return Object.assign({},e,{loading:!0,detailsData:[]});case l:return Object.assign({},e,{loading:!1,rawData:a.response,expenseData:rt(a.response)});case u:return Object.assign({},e,{loading:!1});case p:return Object.assign({},e,{detailsData:e.expenseData.monthlyExpenses[a.index].expenses});case d:return Object.assign({},e,{activeRadio:a.value});case"EXPENSES_ACTIONS.INCREMENT_JARBAS_SUSPICIONS":return Object.assign({},e,{jarbasSuspicionsCount:e.jarbasSuspicionsCount+1});case"EXPENSES_ACTIONS.RESET_JARBAS_SUSPICIONS":return Object.assign({},e,{jarbasSuspicionsCount:0});default:return Object.assign({},e)}}var rt=function(e){var a={values:[],dates:[],monthlyExpenses:[]};return e.sort((function(e,a){return new Date(e.yearMonth.split("-")[0],e.yearMonth.split("-")[1])-new Date(a.yearMonth.split("-")[0],a.yearMonth.split("-")[1])})),e.forEach((function(e){var t=e.yearMonth.split("-")[0],n=e.yearMonth.split("-")[1];a.values.push(e.value.toFixed(2)),a.dates.push("".concat(n,"/").concat(t)),a.monthlyExpenses.push(e)})),a},it={loading:!1,data:[]};function ct(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:it,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case Oe:return Object.assign({},e,{loading:!0});case xe:return Object.assign({},e,{data:a.response,loading:!1});case we:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var ot={profile:{},loading:!1};function st(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:ot,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case Fa:return Object.assign({},e,{loading:!0});case Ha:return Object.assign({},e,{profile:a.response,loading:!1});case Ua:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var lt={propositions:[],loading:!1};function ut(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:lt,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case ma:return Object.assign({},e,{loading:!0});case pa:return Object.assign({},e,{propositions:a.response,loading:!1});case da:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var mt=t(110),pt=Object(mt.c)((function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},a=arguments.length>1?arguments[1]:void 0;return{search:at(e.search,a),expenses:nt(e.expenses,a),news:ct(e.news,a),header:st(e.header,a),propositions:ut(e.propositions,a)}}),Object(mt.a)($a.a));c.a.render(r.a.createElement(o.a,{store:pt},r.a.createElement(Za,null)),document.getElementById("root"))},52:function(e,a,t){e.exports={container:"Footer_container__3kWPf",left:"Footer_left__uY5DG",right:"Footer_right__1M0AE",title:"Footer_title__jkorD",text:"Footer_text__2kZeQ",links:"Footer_links__vd-YX"}},72:function(e,a,t){e.exports={card:"ExpenseDetailsCard_card__35r8g",money:"ExpenseDetailsCard_money__3Lsi-",date:"ExpenseDetailsCard_date__1JHBl",moneyIcon:"ExpenseDetailsCard_moneyIcon__3VSVi",icon:"ExpenseDetailsCard_icon__iZpOJ",detailsContainer:"ExpenseDetailsCard_detailsContainer__27163",jarbasReimbursementContainer:"ExpenseDetailsCard_jarbasReimbursementContainer__2Nxlr"}},92:function(e,a,t){e.exports=t.p+"static/media/perfil-nobackground.66ba5b2a.png"}},[[280,1,2]]]);
//# sourceMappingURL=main.fe9e64e9.chunk.js.map