package br.sp.fatec.api.dw.empresa.servico;

import br.sp.fatec.api.dw.empresa.modelo.*;
import br.sp.fatec.api.dw.empresa.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.Objects;

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

    public void atualizar(EmpresaModelo modelo) {
        try {
            EmpresaModelo empresaModelo = listaPorCnpj(modelo.getCnpj().getCnpj());
            
            empresaModelo.setNivel(modelo.getNivel());
            empresaModelo.setVendedor(modelo.getVendedor());

            repository.save(empresaModelo);
        }catch (NullPointerException e){
            e.getStackTrace();
        }
    }

    public void readCsv() throws IOException {
//        //CNAE
//        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_cnae.csv");
//        FileInputStream stream = new FileInputStream(file);
//        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
//        String proLinha = lerArq.readLine();
//
//        int k=0;
//        while (proLinha != null){
//            if(k == 0){
//                k++;
//                proLinha = lerArq.readLine();
//                continue;
//            }
//            String[] proDados = proLinha.split(",");
//            String id = proDados[0];
//            String codigo = proDados[1];
//            String descricao = proDados[2].replace("\"", "");
//
//            CnaeModelo cnae = new CnaeModelo();
//            cnae.setIdCnae(Integer.parseInt(id));
//            cnae.setCodigo(Integer.parseInt(codigo));
//            cnae.setDescricao(descricao);
//
//            repositoryCnae.save(cnae);
//
//            System.out.println("id : " + id +
//                    " - codigo : " + codigo +
//                    " - descricao : " + descricao);
//
//            proLinha = lerArq.readLine();
//        }
//        lerArq.close();

//        // CIDADE
//        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_cidade.csv");
//        FileInputStream stream = new FileInputStream(file);
//        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
//        String proLinha = lerArq.readLine();
//
//        int k = 0;
//        while (proLinha != null){
//            if(k == 0){
//                k++;
//                proLinha = lerArq.readLine();
//                continue;
//            }
//            String[] proDados = proLinha.split(",");
//            String id = proDados[0];
//            String nome = proDados[1].replace("\"", "");
//            String estado = proDados[2].replace("\"", "");
//            String regiao = proDados[3].replace("\"", "");
//
//            CidadeModelo cidade = new CidadeModelo();
//            cidade.setIdCidade(Integer.parseInt(id));
//            cidade.setNome(nome);
//            cidade.setEstado(estado);
//            cidade.setRegiao(regiao);
//
//            repositoryCidade.save(cidade);
//
//            System.out.println("id : " + id +
//                    " - nome : " + nome +
//                    " - estado : " + estado +
//                    " - regiao : " + regiao);
//
//            proLinha = lerArq.readLine();
//        }
//        lerArq.close();

//        // EMPRESA DESC
//        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\Externo\\base_empresas_det.csv");
//        FileInputStream stream = new FileInputStream(file);
//        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
//        String proLinha = lerArq.readLine();
//
//        int k = 0;
//        while (proLinha != null){
//            if(k == 0){
//                k++;
//                proLinha = lerArq.readLine();
//                continue;
//            }
//            String[] proDados = proLinha.split(",");
//            String cnpj = proDados[2];
//            String nome = proDados[8].replace("\"", "");
//            String porte = proDados[9].replace("\"", "");
//            String tipo = proDados[12].replace("\"", "");
//            String situacao = proDados[10].replace("\"", "");
//            String dataAbertura = proDados[0].replace("\"", "");
//            String email = proDados[3].replace("\"", "");
//            String telefone = proDados[11].replace("\"", "");
//            String naturezaJuridica = proDados[7].replace("\"", "");
//
//            EmpresaDescModelo empresadesc = new EmpresaDescModelo();
//            empresadesc.setCnpj(cnpj);
//            empresadesc.setNome(nome);
//            empresadesc.setPorte(porte);
//            empresadesc.setTipo(tipo);
//            empresadesc.setSituacao(situacao);
//            empresadesc.setDataAbertura(dataAbertura);
//            empresadesc.setEmail(email);
//            empresadesc.setTelefone(telefone);
//            empresadesc.setNaturezaJuridica(naturezaJuridica);
//
//            repositoryDesc.save(empresadesc);
//
//            System.out.println("cpnj : " + cnpj +
//                    " - nome : " + nome +
//                    " - porte : " + porte +
//                    " - tipo : " + tipo +
//                    " - situacao : " + situacao +
//                    " - dataAbertura : " + dataAbertura +
//                    " - email : " + email +
//                    " - telefone : " + telefone +
//                    " - naturezaJuridica : " + naturezaJuridica);
//
//            proLinha = lerArq.readLine();
//        }
//        lerArq.close();

//        // CONSUMO
//        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_consumo.csv");
//        FileInputStream stream = new FileInputStream(file);
//        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
//        String proLinha = lerArq.readLine();
//
//        int k = 0;
//        while (proLinha != null){
//            if(k == 0){
//                k++;
//                proLinha = lerArq.readLine();
//                continue;
//            }
//            String[] proDados = proLinha.split(",");
//            String cnpj = proDados[1];
//            String mes_referencia = proDados[0];
//            String id_produto = proDados[2];
//            String consumo = proDados[3];
//
//            EmpresaDescModelo desc = repositoryDesc.findByCnpj(cnpj);
//
//            if (desc != null) {
//                ConsumoModelo consumoModelo = new ConsumoModelo();
//                consumoModelo.setCnpj(desc);
//                consumoModelo.setMes_referencia(mes_referencia);
//                consumoModelo.setId_produto(id_produto);
//                consumoModelo.setConsumo(Integer.parseInt(consumo));
//
//                repositoryConsumo.save(consumoModelo);
//
//                System.out.println("cpnj : " + cnpj +
//                        " - mes_referencia : " + mes_referencia +
//                        " - id_produto : " + id_produto +
//                        " - consumo : " + consumo);
//            }
//
//            proLinha = lerArq.readLine();
//        }
//        lerArq.close();

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
            String idCidade = proDados[1];
            String idCnae = proDados[2];
            String origem = proDados[3].replace("\"", "");

            EmpresaDescModelo desc = repositoryDesc.findByCnpj(cnpj);

            CidadeModelo cidadeModelo = new CidadeModelo();
            if(idCidade.equals("")){
                cidadeModelo = null;
            } else {
                cidadeModelo = repositoryCidade.findByIdCidade(Integer.parseInt(idCidade));
            }

            CnaeModelo cnaeModelo = new CnaeModelo();
            if (idCnae.equals("")){
                cnaeModelo = null;
            } else {
                cnaeModelo = repositoryCnae.findByIdCnae(Integer.parseInt(idCnae));
            }

            if (desc != null) {
                EmpresaModelo empresa = new EmpresaModelo();
                empresa.setCnpj(desc);
                empresa.setIdCidade(cidadeModelo);
                empresa.setIdCnae(cnaeModelo);
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
}
