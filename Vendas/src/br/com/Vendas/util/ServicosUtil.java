package br.com.Vendas.util;

public class ServicosUtil {

    public static long contador = 0;
    public static long noUsuarios = 0;
    public static boolean iniciado = false;

    public static boolean startServices() {

        if (ServicosUtil.iniciado) {
            return false;
        }

        TesteAcessoBancoDados testeAcessoBancoDados = new TesteAcessoBancoDados();

        new Thread(testeAcessoBancoDados).start();
        
        ServicosUtil.iniciado=true;

        return true;
    }

}
