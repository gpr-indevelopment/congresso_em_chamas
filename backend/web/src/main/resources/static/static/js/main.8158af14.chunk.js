(this.webpackJsonpfrontend=this.webpackJsonpfrontend||[]).push([[0],{100:function(e,a,t){e.exports={container:"Profile_container__SWH17",name:"Profile_name__JMOmG",party:"Profile_party__3PMIV",image:"Profile_image__2RynM"}},122:function(e,a,t){e.exports={container:"Expenses_container__3ICHp",chart:"Expenses_chart__1TsPI",left:"Expenses_left__3ClwW"}},123:function(e,a,t){e.exports={card:"NewsCard_card__3s1dX",title:"NewsCard_title__1CObI",description:"NewsCard_description__2R_94"}},124:function(e,a,t){e.exports={logo:"LandingForm_logo__1gegc",container:"LandingForm_container__2Uk2e",title:"LandingForm_title__3EbVe"}},126:function(e,a,t){e.exports={container:"HeaderLogo_container__20Gh1",logo:"HeaderLogo_logo__26LU5",title:"HeaderLogo_title__3y5Ck"}},156:function(e,a,t){e.exports={container:"ExpensesDetailsSection_container__1pS1q",click:"ExpensesDetailsSection_click__1Xv2F"}},159:function(e,a,t){e.exports={container:"ExpensesRadio_container__2ostu",radio:"ExpensesRadio_radio__coP3Q"}},162:function(e,a,t){e.exports={timeline:"PropositionTree_timeline__1TGgV",switcher:"PropositionTree_switcher__380y_"}},209:function(e,a,t){e.exports={container:"EmptyData_container__1shmg"}},214:function(e,a,t){e.exports={card:"ProfileCard_card__3CAFp"}},215:function(e,a,t){e.exports={container:"Search_container__2Im4r"}},219:function(e,a,t){e.exports={container:"News_container__1Fofe"}},220:function(e,a,t){e.exports={container:"Landing_container__1aA6w"}},221:function(e,a,t){e.exports={video:"LandingVideo_video__23x1M"}},222:function(e,a,t){e.exports=t.p+"static/media/protests_240.2b216380.mp4"},223:function(e,a,t){e.exports=t.p+"static/media/protests_144.c98cd51d.mp4"},229:function(e,a,t){e.exports={container:"Propositions_container__1yv-e"}},230:function(e,a,t){e.exports={container:"Header_container__3Pwhh"}},234:function(e,a,t){e.exports=t.p+"static/media/github-icon.213b75c7.png"},235:function(e,a,t){e.exports={main:"MainContent_main__1nWmk"}},243:function(e,a,t){e.exports=t(438)},248:function(e,a,t){},33:function(e,a,t){e.exports={container:"FeaturesShowcase_container__3Hyxc",card:"FeaturesShowcase_card__1WCAd",icon:"FeaturesShowcase_icon__OFR3Y",title:"FeaturesShowcase_title__sMAlJ",description:"FeaturesShowcase_description__R32tW"}},437:function(e,a,t){},438:function(e,a,t){"use strict";t.r(a);var n=t(0),r=t.n(n),c=t(14),o=t.n(c),i=(t(248),t(38)),l="EXPENSES_ACTIONS.REQUEST_EXPENSES";var s="EXPENSES_ACTIONS.RECEIVE_EXPENSES";var u="EXPENSES_ACTIONS.FAILED_EXPENSES";function m(e){return{type:u,error:e}}var d="EXPENSES_ACTIONS.SHOW_DETAILS";var p="EXPENSES_ACTIONS.RADIO_CHANGED";var E={url:""};function f(e,a){return function(t){t({type:l});var n="".concat(E.url,"/politicians/").concat(e,"/monthlyexpenses"),r=new URLSearchParams(a).toString();fetch("".concat(n,"?").concat(r)).then((function(e){if(e.ok)return e.json();t(m(e))})).then((function(e){return t({type:s,response:e})})).catch((function(e){t(m(e))}))}}var h=t(201);var _=function(e){return r.a.createElement(h.a,{data:{labels:e.data.dates,datasets:[{fill:!1,label:"Despesas",backgroundColor:"#0078ff",borderColor:"#0078ff",data:e.data.values}]},options:{responsive:!0,maintainAspectRatio:!1,onClick:e.onDataClick,scales:{yAxes:[{ticks:{callback:function(e){return"R$ "+e}}}]}}})},v=t(156),g=t.n(v),N=t(443),b=t(237),S=t(84),y=t.n(S),C=t(453),x=t(28),O=t(41),w=t(53);var I=function(e){return r.a.createElement(N.a,{title:r.a.createElement(b.a,{title:e.data.provider},e.data.provider),className:y.a.card,hoverable:e.data.documentUrl,onClick:e.data.documentUrl&&function(){return window.open(e.data.documentUrl,"_blank")},actions:[e.data.documentUrl?r.a.createElement(b.a,{title:"Comprovante"},r.a.createElement("a",{href:e.data.documentUrl,target:"_blank",rel:"noopener noreferrer"},r.a.createElement(x.a,{icon:w.b,key:"document",size:"lg"}))):r.a.createElement(b.a,{title:"Sem comprovante"},r.a.createElement(C.a,null))]},r.a.createElement("p",null,e.data.type),r.a.createElement("div",{className:y.a.money},r.a.createElement(x.a,{icon:O.b,key:"value",size:"lg",className:y.a.moneyIcon}),"R$ "+e.data.value.toLocaleString()),r.a.createElement("div",{className:y.a.date},r.a.createElement(x.a,{icon:w.a,key:"date",size:"lg",className:y.a.icon}),e.data.yearMonth.split("-")[1]+"/"+e.data.yearMonth.split("-")[0]))};var R=function(e){var a=[];return e.data.forEach((function(e){a.push(r.a.createElement(I,{data:e,key:e.documentNumber}))})),r.a.createElement("div",{className:g.a.container},e.data.length>0?a:r.a.createElement("p",{className:g.a.click},"Clique em uma despesa no gr\xe1fico para visualizar os detalhes."))},P=t(122),D=t.n(P),k=t(440),j=t(441),A=t(209),T=t.n(A),L=t(86);var F=function(){return r.a.createElement("div",{className:T.a.container},r.a.createElement(L.a,{description:"Sem dados"}))},H=t(439),U=t(159),z=t.n(U);var M=function(e){return r.a.createElement("div",{className:z.a.container},r.a.createElement(H.default.Group,{defaultValue:e.active,buttonStyle:"solid",onChange:function(a){return e.onChange(a.target.value)},className:z.a.radio},r.a.createElement(H.default.Button,{value:1},"Esse ano"),r.a.createElement(H.default.Button,{value:2},"\xdaltimos 6 meses"),r.a.createElement(H.default.Button,{value:3},"Legislatura")))};var q=function(e){var a=e.handleExpensesRequest,t=e.activeRadio,c=new URLSearchParams(window.location.search).get("politician");return Object(n.useEffect)((function(){switch(t){case 2:a(c,{lastMonths:6});break;case 3:a(c);break;default:a(c,{years:(new Date).getFullYear()})}}),[a,c,t]),r.a.createElement(k.a,{tip:"Carregando...",spinning:e.loading},r.a.createElement(Da,{politicianId:c}),r.a.createElement(Ua,null,e.expenseData.monthlyExpenses&&e.expenseData.monthlyExpenses.length>0?r.a.createElement("div",{className:D.a.container},r.a.createElement(j.a,{direction:"vertical",className:D.a.left,size:"middle"},r.a.createElement(M,{active:e.activeRadio,onChange:e.handleRadioChanged}),r.a.createElement("div",{className:D.a.chart},r.a.createElement(_,{data:e.expenseData,onDataClick:function(a,t){return t.length>0&&e.handleDetailsClick(t[0]._index)}}))),r.a.createElement(R,{data:e.detailsData})):r.a.createElement(F,null)),r.a.createElement(La,null))},V=Object(i.b)((function(e){return e.expenses}),(function(e){return{handleExpensesRequest:function(a,t){return e(f(a,t))},handleDetailsClick:function(a){return e(function(e){return{type:d,index:e}}(a))},handleRadioChanged:function(a){return e(function(e){return{type:p,value:e}}(a))}}}))(q),W=t(449),X=t(214),B=t.n(X),G=t(442),Q=t(39),J=t(454);var K=function(e){var a=N.a.Meta;return r.a.createElement(N.a,{hoverable:!0,className:B.a.card,actions:[r.a.createElement(b.a,{title:"Despesas"},r.a.createElement(Q.a,{to:"/expenses?politician=".concat(e.profile.id)},r.a.createElement(x.a,{icon:O.a,size:"lg"}))),r.a.createElement(b.a,{title:"Not\xedcias"},r.a.createElement(Q.a,{to:"/news?politician=".concat(e.profile.id)},r.a.createElement(x.a,{icon:O.c,size:"lg"}))),r.a.createElement(b.a,{title:"Proposi\xe7\xf5es"},r.a.createElement(Q.a,{to:"/propositions?politician=".concat(e.profile.id)},r.a.createElement(x.a,{icon:w.b,size:"lg"})))]},r.a.createElement(a,{avatar:r.a.createElement(G.a,{size:96,src:e.profile.picture,icon:r.a.createElement(J.a,null)}),title:r.a.createElement(b.a,{title:e.profile.name},e.profile.name),description:e.profile.party}))},Y=t(215),$=t.n(Y);var Z=function(e){var a=e.handleSearchRequest;return Object(n.useEffect)((function(){var e=new URLSearchParams(window.location.search).get("politicianName");e&&a(e)}),[a]),r.a.createElement(k.a,{tip:"Carregando...",spinning:e.loading},r.a.createElement(Da,null,r.a.createElement(Na,{handleSearchSubmit:function(a){return e.handleSearchRequest(a)}})),r.a.createElement(Ua,null,r.a.createElement("div",{className:$.a.container},e.profiles.length>0?function(e){var a=[];return e.forEach((function(e){a.push(r.a.createElement(K,{profile:e,key:e.id}))})),a}(e.profiles):r.a.createElement(W.a,{status:"404",title:"Nenhum pol\xedtico foi encontrado. Tente novamente com outro nome."}))),r.a.createElement(La,null))},ee="SEARCH_ACTIONS.REQUEST_SEARCH";var ae="SEARCH_ACTIONS.RECEIVE_SEARCH";var te="SEARCH_ACTIONS.FAILED_SEARCH";function ne(e){return{type:te,error:e}}function re(e){return function(a){a(function(e){return{type:ee,politicianName:e}}(e)),fetch("".concat(E.url,"/profiles?name=").concat(e)).then((function(e){if(e.ok)return e.json();a(ne(e))})).then((function(e){return a({type:ae,response:e})})).catch((function(e){a(ne(e))}))}}var ce=Object(i.b)((function(e){return e.search}),(function(e){return{handleSearchRequest:function(a){return e(re(a))}}}))(Z),oe=t(123),ie=t.n(oe);var le=function(e){return r.a.createElement(N.a,{cover:r.a.createElement("img",{alt:"",src:e.data.imageLink}),className:ie.a.card,hoverable:!0,onClick:function(){return window.open(e.data.link,"_blank")}},r.a.createElement("h4",{className:ie.a.title},e.data.title),r.a.createElement("small",null,e.data.sourceName+" - "+new Date(e.data.publishedDate).toLocaleDateString()),r.a.createElement("p",{className:ie.a.description},e.data.description))},se=t(219),ue=t.n(se);var me=function(e){var a=e.handleExpensesRequest,t=new URLSearchParams(window.location.search).get("politician");return Object(n.useEffect)((function(){a(t)}),[a,t]),r.a.createElement(k.a,{spinning:e.loading,tip:"Carregando..."},r.a.createElement(Da,{politicianId:t}),r.a.createElement(Ua,null,e.data.length>0?r.a.createElement("div",{className:ue.a.container},function(e){var a=[];return e.forEach((function(e){a.push(r.a.createElement(le,{data:e,key:e.link}))})),a}(e.data)):r.a.createElement(F,null)),r.a.createElement(La,null))},de="NEWS_ACTIONS.REQUEST_NEWS";var pe="NEWS_ACTIONS.RECEIVE_NEWS";var Ee="NEWS_ACTIONS.FAILED_NEWS";function fe(e){return{type:Ee,error:e}}function he(e){return function(a){a({type:de}),fetch("".concat(E.url,"/politicians/").concat(e,"/news")).then((function(e){if(e.ok)return e.json();a(fe(e))})).then((function(e){return a({type:pe,response:e})})).catch((function(e){a(fe(e))}))}}var _e=Object(i.b)((function(e){return e.news}),(function(e){return{handleExpensesRequest:function(a){return e(he(a))}}}))(me),ve=t(220),ge=t.n(ve),Ne=t(76),be=t.n(Ne),Se=t(124),ye=t.n(Se),Ce=t(32),xe=Object(Ce.a)();var Oe=function(e){return r.a.createElement("div",{className:ye.a.container},r.a.createElement("img",{src:be.a,className:ye.a.logo,alt:"Not available"}),r.a.createElement("h1",{className:ye.a.title},"Congresso em chamas"),r.a.createElement(Na,{handleSearchSubmit:function(e){return xe.push("/search?politicianName=".concat(e))}}))},we=t(221),Ie=t.n(we),Re=t(222),Pe=t.n(Re),De=t(223),ke=t.n(De);var je=function(){return r.a.createElement("video",{autoPlay:"autoplay",loop:"loop",muted:!0,className:Ie.a.video},r.a.createElement("source",{src:Pe.a,type:"video/mp4"}),r.a.createElement("source",{src:ke.a,type:"video/mp4"}),"This video is not available.")},Ae=t(33),Te=t.n(Ae);var Le=function(){var e="var(--theme-bg-color)";return r.a.createElement("div",{className:Te.a.container},r.a.createElement("div",{className:Te.a.card},r.a.createElement("div",{className:Te.a.icon},r.a.createElement(x.a,{icon:O.a,size:"7x",color:e})),r.a.createElement("h3",{className:Te.a.title},"Despesas"),r.a.createElement("p",{className:Te.a.description},"Acompanhe todas as despesas lan\xe7adas na cota parlamentar dos deputados federais.")),r.a.createElement("div",{className:Te.a.card},r.a.createElement("div",{className:Te.a.icon},r.a.createElement(x.a,{icon:O.c,size:"7x",color:e})),r.a.createElement("h3",{className:Te.a.title},"Not\xedcias"),r.a.createElement("p",{className:Te.a.description},"Os \xfaltimos artigos e not\xedcias dos deputados federais com a curadoria do Google News API.")),r.a.createElement("div",{className:Te.a.card},r.a.createElement("div",{className:Te.a.icon},r.a.createElement(x.a,{icon:w.b,size:"7x",color:e})),r.a.createElement("h3",{className:Te.a.title},"Proposi\xe7\xf5es"),r.a.createElement("p",{className:Te.a.description},"Projetos de lei, resolu\xe7\xf5es, medidas provis\xf3rias e mais. Acompanhe a atividade dos deputados federais na c\xe2mara.")))};var Fe=function(e){return r.a.createElement("div",null,r.a.createElement("div",{className:ge.a.container},r.a.createElement(Oe,null),r.a.createElement(je,null)),r.a.createElement(Le,null),r.a.createElement(La,null))},He=t(99),Ue=t.n(He),ze=t(456),Me=t(98),qe=t(452),Ve=t(450),We=t(447),Xe=t(162),Be=t.n(Xe),Ge=t(455);var Qe=function(e){var a=Object(n.useState)([]),t=Object(Me.a)(a,2),c=t[0],o=t[1],i=Object(n.useState)([]),l=Object(Me.a)(i,2),s=l[0],u=l[1],m=function(e){e.processingHistory.sort((function(e,a){return a.sequence-e.sequence}));var a=e.processingHistory.map((function(e){return r.a.createElement(Ve.a.Item,{color:"var(--theme-bg-color)",label:new Date(e.timestamp).toLocaleDateString(),key:e.id},r.a.createElement("p",null,e.description))}));return r.a.createElement(Ve.a,{mode:"right",className:Be.a.timeline},a)},d=function(e){return e<10?[13,0]:e<100?[17,0]:[20,0]};return r.a.createElement(We.a,{treeData:function(e){var a=[{title:r.a.createElement(qe.a,{style:{backgroundColor:"var(--theme-bg-color)"},count:e.authors.length,offset:d(e.authors.length)},"Autores"),key:"0-0",children:[]},{title:r.a.createElement(qe.a,{style:{backgroundColor:"var(--theme-bg-color)"},count:e.processingHistory.length,offset:d(e.processingHistory.length)},"Hist\xf3rico de tramita\xe7\xe3o"),key:"0-1",children:[{title:m(e),key:"0-1-0"}]}];return e.authors.forEach((function(e,t){a[0].children.push({title:e,key:"0-0-"+t})})),a}(e.proposition),onSelect:function(e,a){return function(e,a){var t=a.node;t.props.isLeaf?u(e):t.props.expanded?o(c.filter((function(e){return e!==t.props.eventKey}))):o(c.concat(t.props.eventKey))}(e,a)},onExpand:function(e){return o(e)},selectedKeys:s,expandedKeys:c,switcherIcon:r.a.createElement(Ge.a,{className:Be.a.switcher})})};var Je=function(e){var a=e.proposition,t=a.title,n=a.link,c=a.typeDescription;return r.a.createElement(N.a,{className:Ue.a.card},r.a.createElement("h3",null,t),r.a.createElement("h4",{className:Ue.a.type},c),r.a.createElement("div",{className:Ue.a.details},r.a.createElement(Qe,{proposition:e.proposition}),r.a.createElement("a",{href:n,target:"_blank",rel:"noopener noreferrer"},r.a.createElement(ze.a,{className:Ue.a.pdf}))))},Ke=t(229),Ye=t.n(Ke);var $e=function(e){var a=e.handlePropositionsRequest,t=new URLSearchParams(window.location.search).get("politician");return Object(n.useEffect)((function(){a(t)}),[a,t]),r.a.createElement(k.a,{spinning:e.loading,tip:"Carregando..."},r.a.createElement(Da,{politicianId:t}),r.a.createElement(Ua,null,e.propositions.length>0?r.a.createElement("div",{className:Ye.a.container},e.propositions.map((function(e){return r.a.createElement(Je,{proposition:e,key:e.id})}))):r.a.createElement(F,null)),r.a.createElement(La,null))},Ze="PROPOSITIONS_ACTIONS.REQUEST_PROPOSITIONS";var ea="PROPOSITIONS_ACTIONS.RECEIVE_PROPOSITIONS";var aa="PROPOSITIONS_ACTIONS.FAILED_PROPOSITIONS";function ta(e){return{type:aa,error:e}}function na(e){return function(a){a({type:Ze}),fetch("".concat(E.url,"/politicians/").concat(e,"/propositions")).then((function(e){if(e.ok)return e.json();a(ta(e))})).then((function(e){return a({type:ea,response:e})})).catch((function(e){a(ta(e))}))}}var ra=Object(i.b)((function(e){return e.propositions}),(function(e){return{handlePropositionsRequest:function(a){return e(na(a))}}}))($e),ca=t(445),oa=t(100),ia=t.n(oa);function la(e){return r.a.createElement("div",{className:ia.a.container},r.a.createElement(ca.a,{avatar:!0,paragraph:{rows:2,width:[100,55]},title:!1,loading:e.loading},r.a.createElement(G.a,{size:54,src:e.profile.picture,className:ia.a.image,icon:r.a.createElement(J.a,null)}),r.a.createElement("div",null,r.a.createElement("h4",{className:ia.a.name},e.profile.name),r.a.createElement("h5",{className:ia.a.party},e.profile.party))))}var sa=t(230),ua=t.n(sa),ma=t(451),da=t(167),pa=t(448),Ea=t(446),fa=t(87),ha=t(45),_a=t(85),va=t.n(_a),ga=t(444);var Na=function(e){var a=ga.a.Search;return r.a.createElement(a,{placeholder:"Deputado federal",enterButton:!0,onSearch:function(a){return e.handleSearchSubmit(a)}})};var ba=function(e){var a=window.location.pathname,t=new URLSearchParams(window.location.search).get("politician");return r.a.createElement(pa.a,{visible:e.visible,onClose:e.onClose,placement:"left",title:r.a.createElement(Q.a,{to:"/"},r.a.createElement("div",{className:va.a.title},r.a.createElement("img",{src:be.a,height:70,alt:"No content available"})))},r.a.createElement(Ea.a,null,r.a.createElement("div",{className:va.a.search},r.a.createElement(Na,{handleSearchSubmit:function(e){return xe.push("/search?politicianName=".concat(e))}})),r.a.createElement(Ea.a.Item,{disabled:"/expenses"===a},r.a.createElement(Q.a,{to:"/expenses?politician=".concat(t)},r.a.createElement(fa.a,null,r.a.createElement(ha.a,{span:6,className:va.a.icon},r.a.createElement(x.a,{icon:O.a,size:"lg"})),r.a.createElement(ha.a,{span:18},"Despesas")))),r.a.createElement(Ea.a.Item,{disabled:"/news"===a},r.a.createElement(Q.a,{to:"/news?politician=".concat(t)},r.a.createElement(fa.a,null,r.a.createElement(ha.a,{span:6,className:va.a.icon},r.a.createElement(x.a,{icon:O.c,size:"lg"})),r.a.createElement(ha.a,{span:18},"Not\xedcias")))),r.a.createElement(Ea.a.Item,{disabled:"/propositions"===a},r.a.createElement(Q.a,{to:"/propositions?politician=".concat(t)},r.a.createElement(fa.a,null,r.a.createElement(ha.a,{span:6,className:va.a.icon},r.a.createElement(x.a,{icon:w.b,size:"lg"})),r.a.createElement(ha.a,{span:18},"Proposi\xe7\xf5es"))))))},Sa=t(457),ya=t(126),Ca=t.n(ya);var xa=function(e){return r.a.createElement("div",{className:Ca.a.container},r.a.createElement("img",{src:be.a,className:Ca.a.logo,alt:"Not available"}),r.a.createElement("h5",{className:Ca.a.title},"Congresso em chamas"))};var Oa="HEADER_ACTIONS.REQUEST_PROFILE";var wa="HEADER_ACTIONS.RECEIVE_PROFILE";var Ia="HEADER_ACTIONS.FAILED_PROFILE";function Ra(e){return{type:Ia,error:e}}function Pa(e){return function(a){a({type:Oa}),fetch("".concat(E.url,"/politicians/").concat(e)).then((function(e){if(e.ok)return e.json();a(Ra(e))})).then((function(e){return a({type:wa,response:e})})).catch((function(e){a(Ra(e))}))}}var Da=Object(i.b)((function(e){return e.header}),(function(e){return{handleProfileLoading:function(a){return e(Pa(a))}}}))((function(e){var a=Object(n.useState)(!1),t=Object(Me.a)(a,2),c=t[0],o=t[1],i=e.handleProfileLoading,l=e.politicianId,s=e.loading;return Object(n.useEffect)((function(){l&&i(l)}),[i,l]),r.a.createElement("div",{className:ua.a.container},l?r.a.createElement(ma.a,{offsetTop:25},r.a.createElement(da.a,{type:"primary",icon:r.a.createElement(Sa.a,null),onClick:function(){return o(!c)}})):r.a.createElement(Q.a,{to:"/"},r.a.createElement(xa,null)),r.a.createElement("div",null,e.children),l&&r.a.createElement(la,{profile:e.profile,loading:l&&s}),r.a.createElement(ba,{visible:c,onClose:function(){return o(!1)}}))})),ka=t(44),ja=t.n(ka),Aa=t(234),Ta=t.n(Aa);var La=function(){return r.a.createElement("footer",{className:ja.a.container},r.a.createElement("div",{className:ja.a.left},r.a.createElement("div",{className:ja.a.title},"Sobre Congresso em Chamas"),r.a.createElement("p",{className:ja.a.text},"Congresso em Chamas \xe9 uma aplica\xe7\xe3o web que entrega ferramentas para o acompanhamento do trabalho dos deputados federais atualmente em mandato. Visamos contribuir com a transpar\xeancia da atividade do legislativo."),r.a.createElement("div",{className:ja.a.title},"Contato e suporte"),r.a.createElement("p",{className:ja.a.text},"Envie um e-mail para: sitecongressoemchamas@gmail.com")),r.a.createElement("div",{className:ja.a.right},r.a.createElement("div",{className:ja.a.title},"Contribua!"),r.a.createElement("div",null,r.a.createElement("p",{className:ja.a.text},"Esse \xe9 um projeto de c\xf3digo aberto, sem fins lucrativos, que depende de doa\xe7\xf5es para manter sua infraestrutura. Agradecemos contribui\xe7\xf5es financeiras ou de novas features."),r.a.createElement("div",{className:ja.a.links},r.a.createElement("form",{action:"https://www.paypal.com/cgi-bin/webscr",method:"post",target:"_top"},r.a.createElement("input",{type:"hidden",name:"cmd",value:"_donations"}),r.a.createElement("input",{type:"hidden",name:"business",value:"DVUECCKXF39N6"}),r.a.createElement("input",{type:"hidden",name:"item_name",value:"Arrecadar fundos para manter a infraestrutura do site Congresso Em Chamas."}),r.a.createElement("input",{type:"hidden",name:"currency_code",value:"BRL"}),r.a.createElement("input",{type:"image",src:"https://www.paypalobjects.com/pt_BR/BR/i/btn/btn_donateCC_LG.gif",border:"0",name:"submit",title:"PayPal - The safer, easier way to pay online!",alt:"Fa\xe7a doa\xe7\xf5es com o bot\xe3o do PayPal"}),r.a.createElement("img",{alt:"Not available",border:"0",src:"https://www.paypal.com/pt_BR/i/scr/pixel.gif",width:"1",height:"1"})),r.a.createElement("a",{href:"https://github.com/gpr-indevelopment/congresso_em_chamas",target:"_blank",rel:"noopener noreferrer"},r.a.createElement("img",{src:Ta.a,height:30,width:30,alt:"Not available"}))))))},Fa=t(235),Ha=t.n(Fa);var Ua=function(e){return r.a.createElement("main",{className:Ha.a.main},e.children)},za=t(24);t(437);var Ma=function(){return r.a.createElement(za.b,{history:xe},r.a.createElement(za.c,null,r.a.createElement(za.a,{exact:!0,path:"/"},r.a.createElement(Fe,null)),r.a.createElement(za.a,{path:"/search"},r.a.createElement(ce,null)),r.a.createElement(za.a,{exact:!0,path:"/news"},r.a.createElement(_e,null)),r.a.createElement(za.a,{exact:!0,path:"/propositions"},r.a.createElement(ra,null)),r.a.createElement(za.a,{exact:!0,path:"/expenses"},r.a.createElement(V,null))))},qa=t(236),Va={profiles:[],loading:!1};function Wa(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:Va,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case ee:return xe.push("/search?politicianName=".concat(a.politicianName)),Object.assign({},e,{loading:!0});case ae:return Object.assign({},e,{loading:!1,profiles:a.response});case te:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var Xa={loading:!1,rawData:[],expenseData:{},detailsData:[],activeRadio:1};function Ba(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:Xa,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case l:return Object.assign({},e,{loading:!0,detailsData:[]});case s:return Object.assign({},e,{loading:!1,rawData:a.response,expenseData:Ga(a.response)});case u:return Object.assign({},e,{loading:!1});case d:return Object.assign({},e,{detailsData:e.expenseData.monthlyExpenses[a.index].expenses});case p:return Object.assign({},e,{activeRadio:a.value});default:return Object.assign({},e)}}var Ga=function(e){var a={values:[],dates:[],monthlyExpenses:[]};return e.sort((function(e,a){return new Date(e.yearMonth.split("-")[0],e.yearMonth.split("-")[1])-new Date(a.yearMonth.split("-")[0],a.yearMonth.split("-")[1])})),e.forEach((function(e){var t=e.yearMonth.split("-")[0],n=e.yearMonth.split("-")[1];a.values.push(e.value.toFixed(2)),a.dates.push("".concat(n,"/").concat(t)),a.monthlyExpenses.push(e)})),a},Qa={loading:!1,data:[]};function Ja(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:Qa,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case de:return Object.assign({},e,{loading:!0});case pe:return Object.assign({},e,{data:a.response,loading:!1});case Ee:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var Ka={profile:{},loading:!1};function Ya(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:Ka,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case Oa:return Object.assign({},e,{loading:!0});case wa:return Object.assign({},e,{profile:a.response,loading:!1});case Ia:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var $a={propositions:[],loading:!1};function Za(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:$a,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case Ze:return Object.assign({},e,{loading:!0});case ea:return Object.assign({},e,{propositions:a.response,loading:!1});case aa:return Object.assign({},e,{loading:!1});default:return Object.assign({},e)}}var et=t(95),at=Object(et.c)((function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},a=arguments.length>1?arguments[1]:void 0;return{search:Wa(e.search,a),expenses:Ba(e.expenses,a),news:Ja(e.news,a),header:Ya(e.header,a),propositions:Za(e.propositions,a)}}),Object(et.a)(qa.a));o.a.render(r.a.createElement(i.a,{store:at},r.a.createElement(Ma,null)),document.getElementById("root"))},44:function(e,a,t){e.exports={container:"Footer_container__1o7cS",left:"Footer_left__21ioz",right:"Footer_right__2vj1y",title:"Footer_title__nAjr5",text:"Footer_text__3HzfM",links:"Footer_links__1xYa6"}},76:function(e,a,t){e.exports=t.p+"static/media/perfil-nobackground.66ba5b2a.png"},84:function(e,a,t){e.exports={card:"ExpenseDetailsCard_card__OrfL9",money:"ExpenseDetailsCard_money__3kGY1",date:"ExpenseDetailsCard_date__1oGh0",moneyIcon:"ExpenseDetailsCard_moneyIcon__6cPHC",icon:"ExpenseDetailsCard_icon__16ypb"}},85:function(e,a,t){e.exports={title:"HeaderDrawer_title__26R47",icon:"HeaderDrawer_icon__2IQEd",search:"HeaderDrawer_search__2u90p"}},99:function(e,a,t){e.exports={card:"PropositionsCard_card__1EjFD",type:"PropositionsCard_type__3Y_8u",pdf:"PropositionsCard_pdf__37XmZ",details:"PropositionsCard_details__JJSKu"}}},[[243,1,2]]]);
//# sourceMappingURL=main.8158af14.chunk.js.map