package controlePiloto.Impl;

public class Aeronave {
    private String _marca;    
    private int _chassi;
    private Piloto _piloto;

    public Aeronave(String marca, int chassi, Piloto piloto) throws IllegalArgumentException {
        this(chassi, piloto);
        

    }

    public Aeronave(int chassi, Piloto piloto) throws IllegalArgumentException {
        validarChassi(chassi);

        _piloto  = piloto;
        _piloto.setVeiculo(this);
    }

    
    public String getMarca() {
        return _marca;
    }
    

    public int getChassi() {
        return _chassi;
    }

    public Piloto getPiloto() {
        return _piloto;
    }

    @Override
    public String toString() {
        return "Aeronave [Marca=" + _marca + ", chassi=" + _chassi + ", Piloto=" + _piloto + "]";
    }

    private void validarChassi(int chassi) throws IllegalArgumentException {
        if (chassi < 1000000 || chassi > 9999999) {
            throw new IllegalArgumentException("Chassi inválido, o valor aceito é entre 1000000 e 9999999!");
        }
    }
}

