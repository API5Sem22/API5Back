package br.sp.fatec.api.dw.empresa.servico;

import br.sp.fatec.api.dw.empresa.modelo.EmpresaDescModelo;
import br.sp.fatec.api.dw.empresa.modelo.EmpresaModelo;
import br.sp.fatec.api.dw.empresa.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class EmpresaServico {

    private final EmpresaRepositorio repository;

    @Autowired
    public EmpresaServico(EmpresaRepositorio repository) {
        this.repository = repository;
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
        //CNAE
        File file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_cnae.csv");
        FileInputStream stream = new FileInputStream(file);
        BufferedReader lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        String proLinha = lerArq.readLine();

        while (proLinha != null){
            String[] proDados = proLinha.split(",");
            String id = proDados[0];
            String codigo = proDados[1];
            String descricao = proDados[2];

            System.out.println("id : " + id +
                    " - codigo : " + codigo +
                    " - descricao : " + descricao);

            proLinha = lerArq.readLine();
        }
        lerArq.close();

        // CIDADE
        file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_cidade.csv");
        stream = new FileInputStream(file);
        lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        proLinha = lerArq.readLine();

        while (proLinha != null){
            String[] proDados = proLinha.split(",");
            String id = proDados[0];
            String nome = proDados[1];
            String estado = proDados[2];
            String regiao = proDados[3];

            System.out.println("id : " + id +
                    " - nome : " + nome +
                    " - estado : " + estado +
                    " - regiao : " + regiao);

            proLinha = lerArq.readLine();
        }
        lerArq.close();

        // EMPRESA DESC
        file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\Externo\\base_empresas_det.csv");
        stream = new FileInputStream(file);
        lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        proLinha = lerArq.readLine();

        while (proLinha != null){
            String[] proDados = proLinha.split(",");
            String cnpj = proDados[2];
            String nome = proDados[8];
            String porte = proDados[9];
            String tipo = proDados[12];
            String situacao = proDados[10];
            String dataAbertura = proDados[0];
            String email = proDados[3];
            String telefone = proDados[11];
            String naturezaJuridica = proDados[7];

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

        // CONSUMO
        file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_consumo.csv");
        stream = new FileInputStream(file);
        lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        proLinha = lerArq.readLine();

        while (proLinha != null){
            String[] proDados = proLinha.split(",");
            String cnpj = proDados[1];
            String mes_referencia = proDados[0];
            String id_produto = proDados[2];
            String consumo = proDados[3];

            System.out.println("cpnj : " + cnpj +
                    " - mes_referencia : " + mes_referencia +
                    " - id_produto : " + id_produto +
                    " - consumo : " + consumo);

            proLinha = lerArq.readLine();
        }
        lerArq.close();

        // EMPRESA
        file = new File("D:\\AulasEAD-5Sem2022\\API\\BasedeDados\\base_empresas.csv");
        stream = new FileInputStream(file);
        lerArq = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));
        proLinha = lerArq.readLine();

        while (proLinha != null){
            String[] proDados = proLinha.split(",");
            String cnpj = proDados[0];
            String idCidade = proDados[1];
            String idCnae = proDados[2];
            String origem = proDados[3];

            System.out.println("cpnj : " + cnpj +
                    " - idCidade : " + idCidade +
                    " - idCnae : " + idCnae +
                    " - origem : " + origem);

            proLinha = lerArq.readLine();
        }
        lerArq.close();
    }

}
