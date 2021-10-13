# Trabajo Paradigmas de la Programación

## Autor

- Guerreiro Emmanuel
- Legajo: 47262
- Curso: 2k7

## Consideraciones

- El trabajo está subido en el [repositorio](https://github.com/Emmanuel-Guerreiro/trabajo-pp) de Github
- Esta realizado bajo la version de java 11.0.11

## Comentarios sobre la implementación :rocket:

- Todo el trabajo esta realizado en ingles por conveniencia propia.
- Los id de los distintos objetos son definidos automaticamente por los
  constructures. Esto se realiza con una variable de clase, que se
  autoincrementa en cada nueva instanciacion.
- Se ha asumido que todos los atributos son private, a menos que algo indique
  lo contrario
- Se ha asumido que todos los metodos son public, a menos que algo indique lo contrario
- Se ha agregado la clase Card para poder interactuar con el método de
  Request card perteneciente a la clase Customer. (Ver imagen 1 sobre el modelo
  de clases resultante)
- Se ha definido de manera arbitraria valores posibles para los tipos de Loan
  con un enum. Estos son extensibles desde el archivo Types en el package pkgBank.loan
- Para poder correr el programa hay dos opciones.
  - Se definen un conjunto de objetos e interacciones entre estos, en el metodo main.
  - Se corre desde la terminal, con el flag **-a** o **--automatic**, para que corra la funcion encargada de hacer una muestra
