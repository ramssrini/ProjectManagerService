pipeline{

	agent{
		docker 
		{
			image 'java:8'
			
		
		}
	}
	environment
	{
		CI = 'true'
	}
	stages{
		stage('Build'){
			steps{
				echo 'Building......'
				sh 'mvnw install dockerfile:build'
			}
		}
		stage('Test'){
			steps{
				echo 'Testing......'
			}
		}
	
	}

}