package steps;

import dto.RootObject;
import endpoint.FindPetsByStatusEndpoint;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FindPetsByStatusApiSteps {

    FindPetsByStatusEndpoint findPetsByStatusEndpoint = new FindPetsByStatusEndpoint();

    @Step
    public RootObject[] getFindByStatus(String query) {
        return findPetsByStatusEndpoint.getFindByStatus(query);
    }

    @Step
    public void checkStatus(RootObject[] rootStatus, String status) {
        for (int i = 0; i < rootStatus.length; i++) {
            if (rootStatus[i].getStatus() != null) {
                assertThat("", rootStatus[i].getStatus(), equalTo(status));
            }
        }
    }
}
