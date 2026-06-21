package hamburgueria.testes;

import org.junit.jupiter.api.Test;
import hamburgueria.financeiro.proxy.GatewayReal;
import hamburgueria.financeiro.proxy.GatewaySeguroProxy;
import static org.junit.jupiter.api.Assertions.*;

class ProxyTest {
    @Test void gatewayRealAutorizaTokenValido() { assertTrue(new GatewayReal().autorizar(20, "abc")); }
    @Test void gatewayRealNegaValorInvalido() { assertFalse(new GatewayReal().autorizar(0, "abc")); }
    @Test void proxyAutorizaTokenEsperado() { assertTrue(new GatewaySeguroProxy(new GatewayReal(), "ok").autorizar(20, "ok")); }
    @Test void proxyBloqueiaTokenErrado() { assertFalse(new GatewaySeguroProxy(new GatewayReal(), "ok").autorizar(20, "erro")); }
    @Test void proxyContaTentativa() { GatewaySeguroProxy p = new GatewaySeguroProxy(new GatewayReal(), "ok"); p.autorizar(20, "ok"); assertEquals(1, p.getTentativas()); }
    @Test void proxyContaTentativaNegada() { GatewaySeguroProxy p = new GatewaySeguroProxy(new GatewayReal(), "ok"); p.autorizar(20, "erro"); assertEquals(1, p.getTentativas()); }
    @Test void proxyBloqueiaValorInvalidoMesmoComToken() { assertFalse(new GatewaySeguroProxy(new GatewayReal(), "ok").autorizar(0, "ok")); }
}
