package utilities;

public enum ApiResources {

    //Enum class in Java which has collection of constants or methods

        AddPlaceAPI("/maps/api/place/add/json"),
        GetPlaceAPI("/maps/api/place/get/json"),
        DeletePlaceAPI("/maps/api/place/delete/json");
        private String resource;

    ApiResources(String resource) //declaring constructor to place argument
    {
        this.resource = resource; //assigning to the local resource variable
    }

    public String getResource()
    {
        return resource; //returning the resource variable
    }

}
