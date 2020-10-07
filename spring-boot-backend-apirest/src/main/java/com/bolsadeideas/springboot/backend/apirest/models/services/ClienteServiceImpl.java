package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {
	
	private final IClienteDao clienteDao;
	
	@Override
	public List<Cliente> findAll() {
		return this.clienteDao.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return this.clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return this.clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		this.clienteDao.deleteById(id);
	}
}
