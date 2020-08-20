
package model;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aragao
 */
public class Produto {
    private int codProduto;
    private String nomeProduto;
    private double valorVarejo;
    private double valorAtacado;
    private double ganho;//(valorAtacado/valorVarejo)
    private Date dataFab;
    private String sDataFab;
    private Date dataVencimento;
    private String sDataVenc;
    private int diasPraVencer;
    private ProdutoMarca marca;
    private TipoProduto tipoProduto;
    private int quantidade;
    Date hoje = new Date();
           
    public Produto(){
        this.valorAtacado = 0.00;
        this.valorVarejo = 0.01;
    }
    private Date stringToDate(String sDt){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt=null;
        try {
            dt=sdf.parse(sDt);
        } catch (ParseException ex) {
            System.out.println("Erro ao converter: "+ ex.getMessage());
        }
        return dt;
    }
    private void calcVencEmDias(){
        this.dataFab = stringToDate(this.sDataFab);
        this.dataVencimento = stringToDate(this.sDataVenc);
        //SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        long dif = this.dataVencimento.getTime() - hoje.getTime();
        long result = TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS);
        this.diasPraVencer = (int)result;      
    }

    public String getsDataFab() {
        return sDataFab;
    }

    public void setsDataFab(String sDataFab) {
        this.sDataFab = sDataFab;
    }

    public String getsDataVenc() {
        return sDataVenc;
    }

    public void setsDataVenc(String sDataVenc) {
        this.sDataVenc = sDataVenc;
    }
    
    public ProdutoMarca getMarca() {
        return marca;
    }

    public void setMarca(ProdutoMarca marca) {
        this.marca = marca;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    
    ////////////////       
    public int getDiasPraVencer() {
        calcVencEmDias();
        return diasPraVencer;
    }

    public void setDiasPraVencer(int diasPraVencer) {
        this.diasPraVencer = diasPraVencer;
    }
    
    public String calculaGanho(){
        if(this.valorAtacado!=0){
            this.ganho = (((this.valorVarejo/this.valorAtacado)-1)*100);
        }
        NumberFormat porcent = NumberFormat.getInstance();
        porcent.setRoundingMode(RoundingMode.UP);
        porcent.setMaximumIntegerDigits(4);
        porcent.setMinimumIntegerDigits(1);
        porcent.setMaximumFractionDigits(2);
        porcent.setMinimumFractionDigits(1);
        String porcentagem = porcent.format(this.ganho);
        return porcentagem;
    }
    
    public double getGanho() {
        return this.ganho;
    }
    
    public void setGanho(float ganho){
        this.valorVarejo += (ganho * this.valorAtacado)/100;
        this.ganho = ganho;
    }
    
    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorVarejo() {
        return valorVarejo;
    }

    public void setValorVarejo(double valorVarejo) {
        this.valorVarejo = valorVarejo;
    }

    public double getValorAtacado() {
        return valorAtacado;
    }

    public void setValorAtacado(double valorAtacado) {
        this.valorAtacado = valorAtacado;
    }

    public Date getDataFab() {
        return dataFab;
    }

    public void setDataFab(Date dataFab) {
        this.dataFab = dataFab;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
