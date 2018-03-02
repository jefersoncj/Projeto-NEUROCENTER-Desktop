package br.com.markConsult.entidades;



public class Colaborador {
	
	private Integer id;
	private String nome;
	private String fantasia;
	private String email;
	private String cep;
        private String municipio;
        private String uf;
        private String logradouro;
	private String numero;
        private String bairro;
	private String complemento;
	private String cpfCnpj;
	private String ie;
	private String im;
	private String fax;
	private String telefone1;
	private String telefone2;
	private String celular1;
	private String celular2;
	private String homePage;
	private String proprietario;
        
	private boolean ehCliente;
	private boolean ehFornecedor;
	private boolean ehRepres;
        
	private String conceitoCliente;
        private String statusCliente;
        private double limiteCreditoCliente;
        
        private String conceitoFornecedor;
        private String statusFornecedor;
        private double limiteCreditoFornecedor;
        
        private String conceitoRepres;
        private String statusRepres;
        private double limiteVendaRepres;
        
        public Colaborador() {
		
	}

    public Colaborador(Integer id, String nome, String fantasia, String email, String cep, String municipio, String uf, String logradouro, String numero, String bairro, String complemento, String cpfCnpj, String ie, String im, String fax, String telefone1, String telefone2, String celular1, String celular2, String homePage, String proprietario, boolean ehCliente, boolean ehFornecedor, boolean ehRepres, String conceitoCliente, String statusCliente, double limiteCreditoCliente, String conceitoFornecedor, String statusFornecedor, double limiteCreditoFornecedor, String conceitoRepres, String statusRepres, double limiteVendaRepres) {
        this.id = id;
        this.nome = nome;
        this.fantasia = fantasia;
        this.email = email;
        this.cep = cep;
        this.municipio = municipio;
        this.uf = uf;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cpfCnpj = cpfCnpj;
        this.ie = ie;
        this.im = im;
        this.fax = fax;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.homePage = homePage;
        this.proprietario = proprietario;
        this.ehCliente = ehCliente;
        this.ehFornecedor = ehFornecedor;
        this.ehRepres = ehRepres;
        this.conceitoCliente = conceitoCliente;
        this.statusCliente = statusCliente;
        this.limiteCreditoCliente = limiteCreditoCliente;
        this.conceitoFornecedor = conceitoFornecedor;
        this.statusFornecedor = statusFornecedor;
        this.limiteCreditoFornecedor = limiteCreditoFornecedor;
        this.conceitoRepres = conceitoRepres;
        this.statusRepres = statusRepres;
        this.limiteVendaRepres = limiteVendaRepres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public boolean isEhCliente() {
        return ehCliente;
    }

    public void setEhCliente(boolean ehCliente) {
        this.ehCliente = ehCliente;
    }

    public boolean isEhFornecedor() {
        return ehFornecedor;
    }

    public void setEhFornecedor(boolean ehFornecedor) {
        this.ehFornecedor = ehFornecedor;
    }

    public boolean isEhRepres() {
        return ehRepres;
    }

    public void setEhRepres(boolean ehRepres) {
        this.ehRepres = ehRepres;
    }

    public String getConceitoCliente() {
        return conceitoCliente;
    }

    public void setConceitoCliente(String conceitoCliente) {
        this.conceitoCliente = conceitoCliente;
    }

    public String getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(String statusCliente) {
        this.statusCliente = statusCliente;
    }

    public double getLimiteCreditoCliente() {
        return limiteCreditoCliente;
    }

    public void setLimiteCreditoCliente(double limiteCreditoCliente) {
        this.limiteCreditoCliente = limiteCreditoCliente;
    }

    public String getConceitoFornecedor() {
        return conceitoFornecedor;
    }

    public void setConceitoFornecedor(String conceitoFornecedor) {
        this.conceitoFornecedor = conceitoFornecedor;
    }

    public String getStatusFornecedor() {
        return statusFornecedor;
    }

    public void setStatusFornecedor(String statusFornecedor) {
        this.statusFornecedor = statusFornecedor;
    }

    public double getLimiteCreditoFornecedor() {
        return limiteCreditoFornecedor;
    }

    public void setLimiteCreditoFornecedor(double limiteCreditoFornecedor) {
        this.limiteCreditoFornecedor = limiteCreditoFornecedor;
    }

    public String getConceitoRepres() {
        return conceitoRepres;
    }

    public void setConceitoRepres(String conceitoRepres) {
        this.conceitoRepres = conceitoRepres;
    }

    public String getStatusRepres() {
        return statusRepres;
    }

    public void setStatusRepres(String statusRepres) {
        this.statusRepres = statusRepres;
    }

    public double getLimiteVendaRepres() {
        return limiteVendaRepres;
    }

    public void setLimiteVendaRepres(double limiteVendaRepres) {
        this.limiteVendaRepres = limiteVendaRepres;
    }

  
}
