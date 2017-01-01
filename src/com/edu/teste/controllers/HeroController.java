package com.edu.teste.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.teste.entity.Hero;

@RestController
@RequestMapping("/hero")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroController {

    private static Hero[] ALL_HEROES = new Hero[] { new Hero(1, "asd"), new Hero(2, "nbbb") };

    @RequestMapping(method = RequestMethod.POST)
    public Hero send(@RequestBody Hero hero) {
	// String resposta = "Hero name: " + hero.getName();
	// return Response.status(201).entity(resposta).build();
	return hero;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Hero get(@PathVariable("id") int id) {
	Hero retorno = null;
	for (Hero hero : ALL_HEROES) {
	    if (hero.getId() == id) {
		retorno = hero;
	    }
	}
	return retorno;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Hero[] getAll() {
	return ALL_HEROES;
    }

}