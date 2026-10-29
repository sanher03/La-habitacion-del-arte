JAVAC := javac
JAVA := java
OUT := build/classes
LIBS := resources/jdatepicker-1.3.4.jar:resources/TeleChargeAndPaySystem8.jar:resources/TicketSystem.jar
CLASSPATH := $(OUT):$(LIBS)
SOURCES := $(shell find src -name '*.java')

.DEFAULT_GOAL := help
.PHONY: help build run seed clean

help:
	@echo "Targets:"
	@echo "  make build  - Compile all Java sources into $(OUT)/"
	@echo "  make run    - Build and launch the Swing application"
	@echo "  make seed   - Build and regenerate sistema_rellenado.dat"
	@echo "  make clean  - Remove compiled classes from $(OUT)/"

build:
	mkdir -p $(OUT)
	$(JAVAC) -encoding UTF-8 -cp "$(LIBS)" -d $(OUT) $(SOURCES)

run: build
	$(JAVA) -cp "$(CLASSPATH)" Interfaz.VentanaPrincipal

seed: build
	$(JAVA) -Djava.awt.headless=true -cp "$(CLASSPATH)" Demostrador.GenerarSistema

clean:
	rm -rf build
