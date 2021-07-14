let pessoas;

const criaAjaxGet = function(url, dados, func) {
    let ajax = new XMLHttpRequest();
    ajax.onreadystatechange = func;
    ajax.open("GET", url+"?"+dados, true);
    ajax.send();
};

const criaAjaxPost = function(url, dados, func) {
    let ajax = new XMLHttpRequest();
    ajax.onreadystatechange = func;
    ajax.open("POST", url, true);
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send(dados);
};

const mostrar = function () {
    this.onload = ()=> {
        pessoas = this.responseXML.documentElement.getElementsByTagName("pessoa");
        let txt = "<h2>Lista de Pessoas</h2><table border=1px cellpadding=8px><tr><th>Nome</th><th>Idade</th><th colspan='2'>Opções</th></tr>";
        for (let pessoa of pessoas) {
            let codigo = pessoa.getElementsByTagName("codigo")[0].firstChild.nodeValue;
            let nome = pessoa.getElementsByTagName("nome")[0].firstChild.nodeValue;
            let idade = pessoa.getElementsByTagName("idade")[0].firstChild.nodeValue;
            txt += `<tr><td>${nome}</td><td>${idade}</td>
            <td><button type="button" onclick="editar(${codigo})" >Editar</button></td>
            <td><button type="button" onclick="remover(${codigo})" >Excluir</button></td></tr>`;
        }
        txt += "</table>";
        
        if(pessoas.length == 0) {
            document.getElementById("lista").innerHTML = "";
        } else {
            document.getElementById("lista").innerHTML = txt;
        }
        
        nome = document.getElementById("nome").value="";
        idade = document.getElementById("idade").value="";
        botao = document.getElementById("botao").value="Cadastrar";
    };
};

const remover = function (cod) {
    let nome;
    for(let pessoa of pessoas) {
        let codigo = pessoa.getElementsByTagName("codigo")[0].firstChild.nodeValue;
        if(codigo  == cod) {
            nome = pessoa.getElementsByTagName("nome")[0].firstChild.nodeValue;
            break;
        }
    }
    
    if(confirm("Deseja realmente excluir "+nome+"?")) {
        criaAjaxGet("deletar", `id=${cod}`, function () {
            this.onload = ()=> {
                criaAjaxGet("listar","",mostrar);
                alert(this.responseText);
            };
        });
    };
}

const editar = function (cod) {
    let nome, idade;
    for(let pessoa of pessoas) {
        let codigo = pessoa.getElementsByTagName("codigo")[0].firstChild.nodeValue;
        if(codigo  == cod) {
            nome = pessoa.getElementsByTagName("nome")[0].firstChild.nodeValue;
            idade = pessoa.getElementsByTagName("idade")[0].firstChild.nodeValue;
            break;
        }
    }
    document.getElementById("botao").value = "Atualizar";
    document.getElementById("nome").value = nome;
    document.getElementById("idade").value = idade;
    document.getElementById("id").value = cod;
    
    
    
};

const inserirDados = (e) => {
    e.preventDefault();
    let id = document.getElementById("id").value;
    let nome = document.getElementById("nome").value;
    let idade = document.getElementById("idade").value;
    let dados = `id=${id}&nome=${nome}&idade=${idade}`;
    console.log(id)
    if(id == "") {
        criaAjaxPost("inserir", dados,
        function () {
            this.onload = ()=> {
                alert(this.responseText);
                criaAjaxGet("listar","",mostrar);
            };
        });
    } else {
        criaAjaxPost("editar", dados,
        function () {
            this.onload = ()=> {
                criaAjaxGet("listar","",mostrar);
                alert(this.responseText);
            };
        });
    }
};

document.getElementById("botao").onclick = inserirDados;
onload = ()=> {criaAjaxGet("listar","",mostrar);};
