package ec.carper.ingenio.test

@Singleton
class Aux{
    def getDiaTrabajoId() {
        def dia = ['40288ad075912c0f017591309ea40000','40288ad075912c0f017593c7b1f40036','ff80808175b39a150175b3ce98650025', 'ff80808175d5ab0e0175d6def3e70000']
        return dia[4-1]
    }
}
