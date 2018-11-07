pipeline{

	agent{
		
	}
	environment
	{
		CI = 'true'
	}
	stages{
		stage('Build'){
			steps{
				echo 'Building......'
				sh './mvnw install dockerfile:build'
			}
		}
		stage('Test'){
			steps{
				echo 'Testing......'
			}
		}
	
	}

}