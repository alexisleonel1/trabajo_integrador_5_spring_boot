package com.despensa.service;

import java.util.List;

import com.despensa.dto.ClienteCompraTotalDTO;
import com.despensa.model.Cliente;

public interface IClienteService extends BaseService<Cliente>{

	public List<ClienteCompraTotalDTO> getClientesByVentas();
}
