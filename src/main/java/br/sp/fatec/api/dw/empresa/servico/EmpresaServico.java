package br.sp.fatec.api.dw.empresa.servico;

import br.sp.fatec.api.dw.empresa.modelo.*;
import br.sp.fatec.api.dw.empresa.repositorio.*;
import br.sp.fatec.api.dw.usuarios.modelo.UsuarioModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServico {

    private final EmpresaRepositorio repository;
    private final EmpresaDescRepositorio repositoryDesc;
    private final ConsumoRepositorio repositoryConsumo;
    private final CnaeRepositorio repositoryCnae;
    private final CidadeRepositorio repositoryCidade;

    @Autowired
    public EmpresaServico(EmpresaRepositorio repository, EmpresaDescRepositorio repositoryDesc, ConsumoRepositorio repositoryConsumo, CnaeRepositorio repositoryCnae, CidadeRepositorio repositoryCidade) {
        this.repository = repository;
        this.repositoryDesc = repositoryDesc;
        this.repositoryConsumo = repositoryConsumo;
        this.repositoryCnae = repositoryCnae;
        this.repositoryCidade = repositoryCidade;
    }

    public EmpresaModelo listaPorCnpj(String cnpj){
        EmpresaDescModelo emp = new EmpresaDescModelo();
        emp.setCnpj(cnpj);
        return repository.findByCnpj(emp);
    }

    public List<EmpresaModelo> listaLivres(Integer cnae) {
        final String livre = "LIVRE";
        final String prospeccao = "SPC";
        if (cnae == 0) {
            return repository.findAllByOrigemAndProspeccaoAndVendedor(livre, prospeccao, null);
        } else {
            CnaeModelo cnaeModelo = repositoryCnae.findByCodigo(cnae);
            return repository.findAllByOrigemAndProspeccaoAndVendedorAndIdCnae(livre, prospeccao, null , cnaeModelo);
        }
    }

//    public List<EmpresaModelo> listaLivres(int size, int page, Integer cnae) {
//        final String livre = "LIVRE";
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "origem");
//        if (cnae == 0) {
//            return repository.findAllByOrigem(livre, pageRequest);
//        } else {
//            CnaeModelo cnaeModelo = repositoryCnae.findByCodigo(cnae);
//            return repository.findAllByOrigemAndIdCnae(livre, cnaeModelo, pageRequest);
//        }
//    }

    public List<EmpresaModelo> listaPorVendedorFiltroCnae(String email, Integer cnae){
        UsuarioModelo vendedor = new UsuarioModelo();
        vendedor.setEmail(email);
        if (cnae == 0){
            return repository.findByVendedor(vendedor);
        } else {
            CnaeModelo cnaeModelo = repositoryCnae.findByCodigo(cnae);
            return repository.findByVendedorAndIdCnae(vendedor, cnaeModelo);
        }
    }

    public void atualizar(EmpresaModelo modelo) {

        if(modelo.getCnpj() != null){
            EmpresaModelo empresaModelo = listaPorCnpj(modelo.getCnpj().getCnpj());

            empresaModelo.setNivel(modelo.getNivel());
            empresaModelo.setVendedor(modelo.getVendedor());

            repository.save(empresaModelo);
        }else {
            throw new NullPointerException("Entrada inválida");
        }
    }

    public void atualizaVendedor(EmpresaModelo modelo) {

        if(modelo.getCnpj() != null){
            EmpresaModelo empresaModelo = listaPorCnpj(modelo.getCnpj().getCnpj());

            empresaModelo.setVendedor(modelo.getVendedor());

            repository.save(empresaModelo);
        }else {
            throw new NullPointerException("Entrada inválida");
        }
    }

    public void readCsvCnae() throws IOException {
        // CNAE
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_cnae.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        String proLinha = lerArq.readLine();

        int k=0;
        while (proLinha != null){
            if(k == 0){
                k++;
                proLinha = lerArq.readLine();
                continue;
            }
            String[] proDados = proLinha.split(",");
            String id = proDados[0];
            String codigo = proDados[1];
            String descricao = proDados[2].replace("\"", "");

            CnaeModelo cnae = new CnaeModelo();
            cnae.setId(Integer.parseInt(id));
            cnae.setCodigo(Integer.parseInt(codigo));
            cnae.setDescricao(descricao);

            repositoryCnae.save(cnae);

            System.out.println("id : " + id +
                    " - codigo : " + codigo +
                    " - descricao : " + descricao);

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

    public void readCsvCidade() throws IOException {
        // CIDADE
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_cidade.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        String proLinha = lerArq.readLine();

        int k = 0;
        while (proLinha != null){
            if(k == 0){
                k++;
                proLinha = lerArq.readLine();
                continue;
            }
            String[] proDados = proLinha.split(",");
            String id = proDados[0];
            String nome = proDados[1].replace("\"", "");
            String estado = proDados[2].replace("\"", "");
            String regiao = proDados[3].replace("\"", "");

            CidadeModelo cidade = new CidadeModelo();
            cidade.setId(Integer.parseInt(id));
            cidade.setNome(nome);
            cidade.setEstado(estado);
            cidade.setRegiao(regiao);

            repositoryCidade.save(cidade);

            System.out.println("id : " + id +
                    " - nome : " + nome +
                    " - estado : " + estado +
                    " - regiao : " + regiao);

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

    public void readCsvEmpresaDesc() throws IOException {
        // EMPRESA DESC
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\Externo\\base_empresas_det.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String proLinha = lerArq.readLine();

        int k = 0;
        while (proLinha != null){
            if(k == 0){
                k++;
                proLinha = lerArq.readLine();
                continue;
            }
            String[] proDados = proLinha.split(";");
            String cnpj = proDados[2];
            String nome = (proDados[8].isEmpty()) ? null : proDados[8];
            String porte = (proDados[9].isEmpty()) ? null : proDados[9];
            String tipo = (proDados[12].isEmpty()) ? null : proDados[12];
            String situacao = (proDados[10].isEmpty()) ? null : proDados[10];
            String dataAbertura = (proDados[0].isEmpty()) ? null : proDados[0];
            String email = (proDados[3].isEmpty()) ? null : proDados[3];
            String telefone = (proDados[11].isEmpty()) ? null : proDados[11];
            String naturezaJuridica = (proDados[7].isEmpty()) ? null : proDados[7];

            EmpresaDescModelo empresadesc = new EmpresaDescModelo();
            empresadesc.setCnpj(cnpj);
            empresadesc.setNome(nome);
            empresadesc.setPorte(porte);
            empresadesc.setTipo(tipo);
            empresadesc.setSituacao(situacao);
            empresadesc.setDataAbertura(dataAbertura);
            empresadesc.setEmail(email);
            empresadesc.setTelefone(telefone);
            empresadesc.setNaturezaJuridica(naturezaJuridica);

            repositoryDesc.save(empresadesc);

            System.out.println("cpnj : " + cnpj +
                    " - nome : " + nome +
                    " - porte : " + porte +
                    " - tipo : " + tipo +
                    " - situacao : " + situacao +
                    " - dataAbertura : " + dataAbertura +
                    " - email : " + email +
                    " - telefone : " + telefone +
                    " - naturezaJuridica : " + naturezaJuridica);

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

    public void readCsvEmpresaDescSeminfos() throws IOException {
        // EMPRESA DESC SEM INFOS
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_empresas.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String proLinha = lerArq.readLine();

        int k = 0;
        while (proLinha != null){
            if(k == 0){
                k++;
                proLinha = lerArq.readLine();
                continue;
            }
            String[] proDados = proLinha.split(",");
            String cnpj = proDados[0];
            if(cnpj.length() < 14){
                int numeroZeros = 14 - cnpj.length();
                for(int i = 0; i < numeroZeros; i++){
                    cnpj = "0" + cnpj;
                }
            }

            EmpresaDescModelo desc = repositoryDesc.findByCnpj(cnpj);

            if (desc == null) {
                EmpresaDescModelo empresadesc = new EmpresaDescModelo();
                empresadesc.setCnpj(cnpj);

                repositoryDesc.save(empresadesc);

                System.out.println("cpnj : " + cnpj);
            }

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

    public void readCsvConsumo() throws IOException {
        // CONSUMO
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_consumo.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        String proLinha = lerArq.readLine();

        int k = 0;
        while (proLinha != null){
            if(k == 0){
                k++;
                proLinha = lerArq.readLine();
                continue;
            }
            String[] proDados = proLinha.split(",");
            String cnpj = proDados[1];
            if(cnpj.length() < 14){
                int numeroZeros = 14 - cnpj.length();
                for(int i = 0; i < numeroZeros; i++){
                    cnpj = "0" + cnpj;
                }
            }
            String mes_referencia = proDados[0];
            String id_produto = proDados[2];
            String consumo = proDados[3];

            EmpresaDescModelo desc = repositoryDesc.findByCnpj(cnpj);

            if (desc != null) {
                ConsumoModelo consumoModelo = new ConsumoModelo();
                consumoModelo.setCnpj(desc);
                consumoModelo.setMes_referencia(mes_referencia);
                consumoModelo.setId_produto(id_produto);
                consumoModelo.setConsumo(Integer.parseInt(consumo));

                repositoryConsumo.save(consumoModelo);

                System.out.println("cpnj : " + cnpj +
                        " - mes_referencia : " + mes_referencia +
                        " - id_produto : " + id_produto +
                        " - consumo : " + consumo);
            }

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

    public void readCsv() throws IOException {
        // EMPRESA
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_empresas.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        String proLinha = lerArq.readLine();

        int k = 0;
        while (proLinha != null){
            if(k == 0){
                k++;
                proLinha = lerArq.readLine();
                continue;
            }
            String[] proDados = proLinha.split(",");
            String cnpj = proDados[0];
            if(cnpj.length() < 14){
                int numeroZeros = 14 - cnpj.length();
                for(int i = 0; i < numeroZeros; i++){
                    cnpj = "0" + cnpj;
                }
            }
            String idCidade = proDados[1];
            String idCnae = proDados[2];
            String origem = proDados[3].replace("\"", "");

            EmpresaDescModelo desc = repositoryDesc.findByCnpj(cnpj);

            Optional<CidadeModelo> cidadeModelo;
            if(idCidade.equals("")){
                cidadeModelo = null;
            } else {
                cidadeModelo = repositoryCidade.findById(Integer.parseInt(idCidade));
            }

            Optional<CnaeModelo> cnaeModelo;
            if (idCnae.equals("")){
                cnaeModelo = null;
            } else {
                cnaeModelo = repositoryCnae.findById(Integer.parseInt(idCnae));
            }

            if (desc != null) {
                EmpresaModelo empresa = new EmpresaModelo();
                empresa.setCnpj(desc);
                empresa.setIdCidade(cidadeModelo.get());
                empresa.setIdCnae(cnaeModelo.get());
                empresa.setOrigem(origem);

                repository.save(empresa);

                System.out.println("cpnj : " + cnpj +
                        " - idCidade : " + idCidade +
                        " - idCnae : " + idCnae +
                        " - origem : " + origem);
            }

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

    public void populateNivelEmpresa() throws IOException {
        // EMPRESA
        BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Users\\GabrielSG\\Downloads\\message.txt"));
        String linha = "";

        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                String cnpj = linha.substring(0, 14);
                String nivel = linha.substring(15);

                EmpresaDescModelo desc = new EmpresaDescModelo();
                desc.setCnpj(cnpj);

                EmpresaModelo empresa = repository.findByCnpj(desc);

                if (empresa != null) {
                    empresa.setNivel(nivel);
                    repository.save(empresa);

                    System.out.println("cpnj : " + cnpj +
                            " - nivel : " + nivel);
                }
            } else
                break;
        }
        buffRead.close();
    }

    public void populateProspeccaoEmpresa() throws IOException {
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\dados_livres_ok.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream));
        String proLinha = lerArq.readLine();

        int k = 0;
        while (proLinha != null){
            if(k == 0){
                k++;
                proLinha = lerArq.readLine();
                continue;
            }
            String[] proDados = proLinha.split(";");
            String cnpj = proDados[0];
            if(cnpj.length() < 14){
                int numeroZeros = 14 - cnpj.length();
                for(int i = 0; i < numeroZeros; i++){
                    cnpj = "0" + cnpj;
                }
            }
            String prospeccao = proDados[7];

            EmpresaDescModelo desc = repositoryDesc.findByCnpj(cnpj);
            EmpresaModelo emp = repository.findByCnpj(desc);

            System.out.println("cpnj : " + cnpj +
                    " - prospeccao : " + prospeccao);

            if (emp != null) {
                emp.setProspeccao(prospeccao);

                repository.save(emp);

                System.out.println("ATUALIZADO");
            }

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

    public List<String> listaConsumoCnaes(){
        return repository.findConsumosCnae();
    }

    public List<String> listaConsumoEstados(){
        return repository.findConsumosEstados();
    }
}
